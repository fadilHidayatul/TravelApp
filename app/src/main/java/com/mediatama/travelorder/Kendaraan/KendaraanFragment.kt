package com.mediatama.travelorder.Kendaraan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mediatama.travelorder.Kendaraan.Adapter.KendaraanAdapter
import com.mediatama.travelorder.Kendaraan.Model.Kendaraan
import com.mediatama.travelorder.R
import com.mediatama.travelorder.UtilsApi.ApiClient

import com.mediatama.travelorder.databinding.FragmentKendaraanBinding
import com.tapadoo.alerter.Alerter
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class KendaraanFragment : Fragment() {
    private lateinit var binding : FragmentKendaraanBinding
    private lateinit var adapter : KendaraanAdapter

    private var dataBean = arrayListOf<Kendaraan.DATABean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKendaraanBinding.inflate(inflater,container,false)

        showKendaraan()

        return binding.root
    }

    private fun showKendaraan() {
        ApiClient.getClient.getKendaraan().enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val jsonO = JSONObject(response.body()!!.string())
                    if(jsonO.getString("status") == "200"){
                        val jsonA = jsonO.getJSONArray("DATA")

                        dataBean = ArrayList()
                        val gson = Gson()

                        for (i in 0 until jsonA.length()){
                            val kendaraan : Kendaraan.DATABean = gson.fromJson(jsonA.getJSONObject(i).toString(), Kendaraan.DATABean::class.java)
                            dataBean.add(kendaraan)
                        }

                        adapter = KendaraanAdapter(context, dataBean)
                        binding.recyclerKendaraan.adapter = adapter
                        binding.recyclerKendaraan.layoutManager = LinearLayoutManager(context)
                        binding.recyclerKendaraan.setHasFixedSize(true)

                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Alerter.create(activity)
                    .setTitle("Error")
                    .setText(t.localizedMessage)
                    .setIcon(R.drawable.ic_warning)
                    .setBackgroundColorRes(R.color.black)
                    .show()
            }

        })


    }


}
