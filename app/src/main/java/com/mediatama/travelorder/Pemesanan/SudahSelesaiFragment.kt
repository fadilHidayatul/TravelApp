package com.mediatama.travelorder.Pemesanan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mediatama.travelorder.Pemesanan.Adapter.SudahSelesaiAdapter
import com.mediatama.travelorder.Pemesanan.Model.SudahSelesai

import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.UtilsApi.ApiClient
import com.mediatama.travelorder.databinding.FragmentSudahSelesaiBinding
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class SudahSelesaiFragment : Fragment() {
    private lateinit var binding : FragmentSudahSelesaiBinding
    private lateinit var adapter : SudahSelesaiAdapter
    private lateinit var manager : PrefManager

    private var databean = arrayListOf<SudahSelesai.DATABean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSudahSelesaiBinding.inflate(inflater,container,false)
        manager = PrefManager(requireContext())

        showListSudahBayar()

        return binding.root
    }

    private fun data(){
        binding.recyclerSudahBayar.visibility = View.VISIBLE
        binding.noData.visibility = View.GONE
    }
    private fun noData(){
        binding.recyclerSudahBayar.visibility = View.GONE
        binding.noData.visibility = View.VISIBLE
    }

    private fun showListSudahBayar() {
        ApiClient.getClient.pesananSudahBayar(manager.getID()).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        data()
                        val jsonA = jsonO.getJSONArray("DATA")

                        databean = ArrayList()
                        val gson = Gson()

                        for (i in 0 until jsonA.length()){
                            val sudahselesai : SudahSelesai.DATABean = gson.fromJson(jsonA.getJSONObject(i).toString(), SudahSelesai.DATABean::class.java)
                            databean.add(sudahselesai)
                        }
                        adapter = SudahSelesaiAdapter(context,databean)
                        binding.recyclerSudahBayar.adapter = adapter
                        binding.recyclerSudahBayar.layoutManager = LinearLayoutManager(context)
                        binding.recyclerSudahBayar.setHasFixedSize(true)

                    }else{
                        noData()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage ,Toast.LENGTH_LONG).show()
            }

        })


    }

}
