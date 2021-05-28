package com.mediatama.travelorder.Profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mediatama.travelorder.LoginRegister.LoginActivity

import com.mediatama.travelorder.R
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        manager = PrefManager(requireContext())

        fetchProfile()
        doLogout()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun fetchProfile() {
        binding.namaProfil.text = manager.getNama()
        binding.noProfil.text = manager.getHp()
        binding.genderProfil.text = manager.getJekel()
        binding.usernameProfil.text = manager.getUsername()
        ApiClient.getClient.pesananSudahBayar(manager.getID()).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        val jsonA = jsonO.getJSONArray("DATA")

                        binding.jumlahPesanan.text = jsonA.length().toString()+" Pemesanan"
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    private fun doLogout() {
        binding.logout.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val builder = AlertDialog.Builder(v!!.context)
                builder.setTitle("Keluar Aplikasi")
                    .setCancelable(true)
                    .setPositiveButton("Ya"
                    ) { p0, p1 ->
                        manager.removeSession()
                        activity!!.finish()

                        val intent = Intent(v.context, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }
                    .setNegativeButton("Tidak"
                    ){ dialog,int->
                        dialog.cancel()
                    }

                val alertDialog : AlertDialog = builder.create()
                alertDialog.show()
            }

        })
    }

}
