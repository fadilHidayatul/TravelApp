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
import com.mediatama.travelorder.Home.Adapter.RuteAdapter
import com.mediatama.travelorder.Home.Model.Rute
import com.mediatama.travelorder.UtilsApi.ApiClient

import com.mediatama.travelorder.databinding.FragmentRuteBinding
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class RuteFragment : DialogFragment() {
    private lateinit var binding : FragmentRuteBinding

    private lateinit var adapter : RuteAdapter
    private var databean = arrayListOf<Rute.DATABean>()

    //isilist


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRuteBinding.inflate(inflater,container,false)
        setLayout()
        showRute()

        return binding.root
    }

    private fun showRute() {
        ApiClient.getClient.showRute().enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        val jsonA = jsonO.getJSONArray("DATA")

                        databean = ArrayList()
                        val gson = Gson()

                        for (i in 0 until jsonA.length()){
                            val rute : Rute.DATABean = gson.fromJson(jsonA.getJSONObject(i).toString(), Rute.DATABean::class.java)
                            databean.add(rute)
                        }
                        adapter = RuteAdapter(context, databean)
                        binding.recyclerRute.adapter = adapter
                        binding.recyclerRute.layoutManager = LinearLayoutManager(context)
                        binding.recyclerRute.setHasFixedSize(true)

                    }else{
                        Toast.makeText(context,jsonO.getString("status"),Toast.LENGTH_LONG).show()
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

        val paramsLcl = binding.tesLinear.layoutParams
        paramsLcl.width = widthLcl
        paramsLcl.height = heightLcl
        binding.tesLinear.layoutParams = paramsLcl
    }


}
