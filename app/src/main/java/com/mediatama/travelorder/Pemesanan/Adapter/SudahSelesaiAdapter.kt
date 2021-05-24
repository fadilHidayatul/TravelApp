package com.mediatama.travelorder.Pemesanan.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mediatama.travelorder.Pemesanan.DetailTransaksiActivity
import com.mediatama.travelorder.Pemesanan.UploadBuktiActivity
import com.mediatama.travelorder.databinding.RowBelumBayarBinding
import com.mediatama.travelorder.databinding.RowSudahSelesaiBinding

class SudahSelesaiAdapter(context: Context?) : RecyclerView.Adapter<SudahSelesaiAdapter.viewHolder>() {
    private var mContext : Context = context!!


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SudahSelesaiAdapter.viewHolder {
        val view = RowSudahSelesaiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: SudahSelesaiAdapter.viewHolder, position: Int) {
        holder.binding.ruteSudah.text = "Jakarta - Ambon"
        holder.binding.seeDetailTransaksi.setOnClickListener {
            val intent = Intent(mContext,DetailTransaksiActivity::class.java)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return 8
    }

    class viewHolder(var binding : RowSudahSelesaiBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}