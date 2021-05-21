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
import com.mediatama.travelorder.databinding.FragmentHomeBinding
import com.tapadoo.alerter.Alerter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var manager : PrefManager

    lateinit var datePickerDialogFrom : DatePickerDialog
    lateinit var datePickerDialogTo : DatePickerDialog
    var th : Int = 0
    var bln : Int = 0
    var hr : Int = 0
    private var xmonth : Int = 0
    private var xyear : Int = 0
    private var xday : Int = 0
    private lateinit var tglPergi : String
    private lateinit var tglPulang : String


    fun HomeFragment(){

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        manager = PrefManager(requireContext())

        showPilihan()

        //if intent
        if (manager.getRuteBoleean()){
            binding.selectedRuteHome.text = manager.getRute()
        }else{
            binding.selectedRuteHome.clearComposingText()
        }
        if (manager.getMobilBoolean()){
            binding.selectedMobilHome.text = manager.getMobil()
        }

        buttonPesan()

        return binding.root
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

        datePickerDialogTo = DatePickerDialog(
            requireContext(), R.style.TimePickerTheme,
            OnDateSetListener { view, year, month, dayOfMonth ->
                xmonth = month + 1
                xyear = year
                xday = dayOfMonth

                binding.txtTglPulang.text = "$xday - $xmonth - $xyear"
                tglPulang = "$xyear-$xmonth-$xday"


            }, th, bln, hr
        )
        binding.tglTo.setOnClickListener {
            datePickerDialogTo.datePicker.minDate = System.currentTimeMillis() - 1000
            datePickerDialogTo.show()
        }

    }

    private fun buttonPesan() {
        binding.btnPesan.setOnClickListener {
            var sdf = SimpleDateFormat("dd-MM-yyyy")
            var date1 : Date = sdf.parse(tglPergi)
            var date2 : Date = sdf.parse(tglPulang)

            var finalrute : String = binding.selectedRuteHome.text.toString()
            var finalmobil : String = binding.selectedMobilHome.text.toString()
            var finalkursi : String = binding.jumlahKursi.text.toString()

            var diff : Long = (date2.time - date1.time) / (1000 * 60 * 60 * 24)

            if (diff < 0){
                Alerter.create(this.activity)
                    .setTitle("Tanggal Salah")
                    .setText("Tanggal yang dimasukkan terbalik")
                    .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()

            }else{
                var intent = Intent(context, SuccessPesanActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)

                manager.removeRuteBoolean()
                manager.removeMobilBoolean()

                Toast.makeText(requireContext(), "$finalrute\n$finalmobil\n$finalkursi\n$tglPergi\n$tglPulang", Toast.LENGTH_LONG).show()

            }
        }
    }


}


