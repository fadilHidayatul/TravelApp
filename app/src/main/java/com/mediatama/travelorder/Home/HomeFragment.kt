package com.mediatama.travelorder.Home

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.mediatama.travelorder.MainActivity
import com.mediatama.travelorder.R
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.UtilsApi.ApiClient
import com.mediatama.travelorder.databinding.FragmentHomeBinding
import com.tapadoo.alerter.Alerter
import dmax.dialog.SpotsDialog
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var manager : PrefManager
    private lateinit var dialog : SpotsDialog

    lateinit var datePickerDialogFrom : DatePickerDialog
    lateinit var datePickerDialogTo : DatePickerDialog
    var th : Int = 0
    var bln : Int = 0
    var hr : Int = 0
    private var xmonth : Int = 0
    private var xyear : Int = 0
    private var xday : Int = 0
    private var tglPergi : String = "0000-00-00"

    private var idRute : String = ""
    private var idKendaraan : String = ""
    private var kapasitas : Int = 0
    private var jumlahPesanKursi : String = ""
    private var diff : Long = 0

    private lateinit var pickerVals: Array<String>

    fun HomeFragment(){

    }
    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        manager = PrefManager(requireContext())
        dialog = SpotsDialog.Builder().setContext(requireContext()).setCancelable(false).setMessage("Harap Tunggu").build() as SpotsDialog

        showPilihan()
        numberPicker()

        //if intent
        if (manager.getRuteBoleean()){
            binding.selectedRuteHome.text = manager.getRute()
            idRute = manager.getIdRute()!!
        }else{
            binding.selectedRuteHome.clearComposingText()
        }
        if (manager.getMobilBoolean()){
            binding.selectedMobilHome.text = manager.getMobil()
            idKendaraan = manager.getIdMobil()
            kapasitas = manager.getKapasitasMobil().toInt()
        }else{
            binding.selectedMobilHome.clearComposingText()
        }
        binding.homeUser.text = manager.getNama()

        buttonPesan()

        return binding.root
    }

    private fun numberPicker() {
        pickerVals = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9")

        binding.jumlahKursi.minValue = 1
        binding.jumlahKursi.maxValue = 9
        binding.jumlahKursi.displayedValues = pickerVals

        binding.jumlahKursi.setOnValueChangedListener { numberPicker, i, i1 ->
            val valuePicker1: Int = binding.jumlahKursi.value
            jumlahPesanKursi = valuePicker1.toString()
        }
    }

    private fun showPilihan() {
        val fragmentManager: FragmentManager = (context as MainActivity).supportFragmentManager
        binding.pilihRute.setOnClickListener {
            val dialog = RuteFragment()
            dialog.show(fragmentManager, "Rute Dialog")
        }
        binding.pilihMobil.setOnClickListener{
            val dialog2 = MobilFragment()
            dialog2.show(fragmentManager, "Mobil Dialog")
        }

        val calendar = Calendar.getInstance()
        th = calendar.get(Calendar.YEAR)
        bln = calendar.get(Calendar.MONTH)
        hr = calendar.get(Calendar.DAY_OF_MONTH)

        datePickerDialogFrom = DatePickerDialog(
            requireContext(), R.style.TimePickerTheme,
            OnDateSetListener { view, year, month, dayOfMonth ->
                xmonth = month + 1
                xyear = year
                xday = dayOfMonth

                binding.txtTglPergi.text = "$xday - $xmonth - $xyear"
                tglPergi = "$xyear-$xmonth-$xday"

            }, th, bln, hr
        )
        binding.tglFrom.setOnClickListener {
            datePickerDialogFrom.datePicker.minDate = System.currentTimeMillis() - 1000
            datePickerDialogFrom.show()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buttonPesan() {
        binding.btnPesan.setOnClickListener {
//            val sdf = SimpleDateFormat("dd-MM-yyyy")
//            val date1 : Date = sdf.parse(tglPergi)
//            val date2 : Date = sdf.parse(tglPulang)
//            diff  = ((date2.time - date1.time) / (100 * 60 * 60 * 24 )) / 3600

            when {
                binding.selectedRuteHome.text.toString() == "Pilih Rute" -> {
                    Alerter.create(this.activity)
                        .setText("Harap Pilih Rute")
                        .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
                }
                binding.selectedMobilHome.text.toString() == "Pilih Mobil" -> {
                    Alerter.create(this.activity)
                        .setText("Harap Pilih Mobil")
                        .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
                }
                binding.jumlahKursi.value > kapasitas ->{
                    Alerter.create(this.activity)
                        .setTitle("Kursi Tidak Tersedia")
                        .setText("Pesanan paling banyak hanya bisa $kapasitas kursi")
                        .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
                }
                tglPergi == "0000-00-00" -> {
                    Alerter.create(this.activity)
                        .setText("Pilih Tanggal Pergi")
                        .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
                }
//                diff < 0 -> {
//                    Alerter.create(this.activity)
//                        .setTitle("Tanggal Salah")
//                        .setText("Tanggal yang dimasukkan terbalik")
//                        .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
//                }
                else -> {
                    sendPesananToApi()
                }
            }
        }
    }

    private fun sendPesananToApi() {
        manager = PrefManager(requireContext())
//        val sdf = SimpleDateFormat("yyyy-MM-dd")
//        val currentDateandTime = sdf.format(Date())
        dialog.show()
        ApiClient.getClient.addPesanan(
            manager.getID(),
            idRute,
            idKendaraan,
            tglPergi,
            jumlahPesanKursi
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    dialog.dismiss()
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200") {

                        manager.removeRuteBoolean()
                        manager.removeMobilBoolean()

                        val intent = Intent(context, SuccessPesanActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)

                    } else {
                        Toast.makeText(context, jsonO.getString("message"), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(context, "Cek Koneksi Internet", Toast.LENGTH_LONG).show()
            }

        })

    }

    override fun onPause() {
        super.onPause()
        dialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialog.dismiss()
    }

    override fun onStop() {
        super.onStop()
        dialog.dismiss()
    }


}


