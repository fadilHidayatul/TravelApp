package com.mediatama.travelorder.Kendaraan.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mediatama.travelorder.Kendaraan.DetailKendaraanActivity
import com.mediatama.travelorder.databinding.RowKendaraanBinding

class KendaraanAdapter(context: Context?) : RecyclerView.Adapter<KendaraanAdapter.viewHolder>() {
    private var mContext: Context = context!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KendaraanAdapter.viewHolder {
        val view = RowKendaraanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: KendaraanAdapter.viewHolder, position: Int) {
        holder.binding.namaKendaraan.text = "Suzuki Swift"
        holder.binding.seeDetailKendaraan.setOnClickListener {
            val intent = Intent(mContext,DetailKendaraanActivity::class.java)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    class viewHolder(var binding : RowKendaraanBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}