package com.mediatama.travelorder.Pemesanan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mediatama.travelorder.Pemesanan.Adapter.BelumBayarAdapter
import com.mediatama.travelorder.Pemesanan.Model.BelumBayar
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.UtilsApi.ApiClient

import com.mediatama.travelorder.databinding.FragmentBelumBayarBinding
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class BelumBayarFragment : Fragment() {
    private lateinit var binding : FragmentBelumBayarBinding
    private lateinit var adapter : BelumBayarAdapter
    private lateinit var manager : PrefManager

    private var databean = arrayListOf<BelumBayar.DATABean>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBelumBayarBinding.inflate(inflater,container,false)
        manager = PrefManager(requireContext())

        showListBelumBayar()


        return binding.root
    }

    private fun showListBelumBayar() {
        ApiClient.getClient.pesananBelumBayar(manager.getID()).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        val jsonA = jsonO.getJSONArray("DATA")

                        databean = ArrayList()
                        val gson = Gson()

                        for (i in 0 until jsonA.length()){
                            val belumbayar : BelumBayar.DATABean = gson.fromJson(jsonA.getJSONObject(i).toString(), BelumBayar.DATABean::class.java)
                            databean.add(belumbayar)
                        }

                        adapter = BelumBayarAdapter(context, databean)
                        binding.recyclerBelumBayar.adapter = adapter
                        binding.recyclerBelumBayar.layoutManager = LinearLayoutManager(context)
                        binding.recyclerBelumBayar.setHasFixedSize(true)

                    }else{
                        Toast.makeText(context, jsonO.getString("message"),Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context,t.localizedMessage,Toast.LENGTH_LONG).show()
            }

        })


    }

}
