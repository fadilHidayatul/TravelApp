package com.mediatama.travelorder.Profile

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.crowdfire.cfalertdialog.CFAlertDialog
import com.mediatama.travelorder.LoginRegister.LoginActivity
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.UtilsApi.ApiClient
import com.mediatama.travelorder.databinding.FragmentProfileBinding
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var manager : PrefManager
    private lateinit var alertDialog : CFAlertDialog
    private lateinit var builder : CFAlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        manager = PrefManager(requireContext())

        fetchProfile()

        initialDialog()
        doLogout()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun fetchProfile() {
        binding.namaProfil.text = manager.getNama()
        binding.noProfil.text = manager.getHp()
        binding.genderProfil.text = manager.getJekel()
        binding.usernameProfil.text = manager.getUsername()
        ApiClient.getClient.pesananSudahBayar(manager.getID()).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200") {
                        val jsonA = jsonO.getJSONArray("DATA")

                        binding.jumlahPesanan.text = jsonA.length().toString() + " Pemesanan"
                    }else{
                        binding.jumlahPesanan.text =  "0 Pemesanan"
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context,t.localizedMessage,Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun initialDialog(){

        builder = CFAlertDialog.Builder(requireContext())
            .setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET)
            .setTitle("LOGOUT")
            .setMessage("Apakah anda akan keluar dari aplikasi? Anda akan login ulang kembali")
            .addButton(
                "Keluar",
                -1,
                -1,
                CFAlertDialog.CFAlertActionStyle.NEGATIVE,
                CFAlertDialog.CFAlertActionAlignment.JUSTIFIED,
                object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        manager.removeSession()
                        manager.removeRuteBoolean()
                        manager.removeMobilBoolean()
                        activity!!.finish()

                        val intent = Intent(requireContext(), LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }
                })
            .addButton(
                "Tidak",
                Color.parseColor("#66040404"),
                Color.parseColor("#FFFFFF"),
                CFAlertDialog.CFAlertActionStyle.DEFAULT,
                CFAlertDialog.CFAlertActionAlignment.JUSTIFIED,
                object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()
                    }
                })
    }


    private fun doLogout() {
        binding.logout.setOnClickListener(object : View.OnClickListener {
            @SuppressLint("ResourceAsColor")
            override fun onClick(v: View?) {

                alertDialog = builder.show()

            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        alertDialog.dismiss()
    }

    override fun onPause() {
        super.onPause()
        alertDialog.dismiss()
    }


}