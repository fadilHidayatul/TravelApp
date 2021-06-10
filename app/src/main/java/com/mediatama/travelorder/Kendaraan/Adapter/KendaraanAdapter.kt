package com.mediatama.travelorder.Kendaraan.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mediatama.travelorder.Kendaraan.DetailKendaraanActivity
import com.mediatama.travelorder.Kendaraan.Model.Kendaraan
import com.mediatama.travelorder.UtilsApi.ApiClient
import com.mediatama.travelorder.databinding.RowKendaraanBinding
import java.io.Serializable

class KendaraanAdapter(context: Context?, dataBean: ArrayList<Kendaraan.DATABean>) : RecyclerView.Adapter<KendaraanAdapter.viewHolder>() {
    private var mContext: Context = context!!
    private var listKendaraan = dataBean


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KendaraanAdapter.viewHolder {
        val view = RowKendaraanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: KendaraanAdapter.viewHolder, position: Int) {
        val data = listKendaraan[position]

        holder.binding.namaKendaraan.text = data.nama_kendaraan
        Glide.with(mContext)
            .load(ApiClient.THUMBMOBIL_IMG_URL+data.thumbnail)
            .fitCenter()
            .into(holder.binding.fotoKendaraan)

        holder.binding.seeDetailKendaraan.setOnClickListener {
            val intent = Intent(mContext, DetailKendaraanActivity::class.java)

            intent.putExtra("foto", data.foto)
            intent.putExtra("kursi", data.kursi)
            intent.putExtra("transmisi", data.transmisi)
            intent.putExtra("tahun", data.tahun)
            intent.putExtra("plat", data.plat)
            intent.putExtra("nama", data.nama_kendaraan)
            intent.putExtra("desc", data.deskripsi)

            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listKendaraan.size
    }

    class viewHolder(var binding: RowKendaraanBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}




