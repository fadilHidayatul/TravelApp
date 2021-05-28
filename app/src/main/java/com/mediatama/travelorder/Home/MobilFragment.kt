package com.mediatama.travelorder.Home

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mediatama.travelorder.Home.Adapter.MobilAdapter
import com.mediatama.travelorder.Home.Model.SelectKendaraan
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.UtilsApi.ApiClient

import com.mediatama.travelorder.databinding.FragmentMobilBinding
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class MobilFragment : DialogFragment() {
    private lateinit var binding : FragmentMobilBinding
    private lateinit var adapter : MobilAdapter
    private lateinit var manager: PrefManager

    private var databean = arrayListOf<SelectKendaraan.DATABean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMobilBinding.inflate(inflater,container,false)
        manager = PrefManager(requireContext())

        setLayout()
        getDataMobil()

        return binding.root
    }

    private fun getDataMobil() {
        ApiClient.getClient.selectKendaraan(manager.getIdRute()).enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        val jsonA = jsonO.getJSONArray("DATA")

                        databean = ArrayList()
                        val gson = Gson()

                        for (i in 0 until jsonA.length()){
                            val mobil : SelectKendaraan.DATABean = gson.fromJson(jsonA.getJSONObject(i).toString(), SelectKendaraan.DATABean::class.java)
                            databean.add(mobil)
                        }

                        adapter = MobilAdapter(context,databean)
                        binding.recyclerMobil.adapter = adapter
                        binding.recyclerMobil.layoutManager = LinearLayoutManager(context)
                        binding.recyclerMobil.setHasFixedSize(true)

                    }else{
                        Toast.makeText(context,jsonO.getString("status"), Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context,t.localizedMessage,Toast.LENGTH_LONG).show()
            }

        })



    }

    private fun setLayout() {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthLcl = (displayMetrics.widthPixels * 0.9f).toInt()
        val heightLcl = (displayMetrics.heightPixels * 0.7f).toInt()

        val paramsLcl = binding.LayoutMobil.layoutParams
        paramsLcl.width = widthLcl
        paramsLcl.height = heightLcl
        binding.LayoutMobil.layoutParams = paramsLcl
    }

}
