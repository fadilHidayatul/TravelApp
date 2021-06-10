package com.mediatama.travelorder.Pemesanan.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mediatama.travelorder.Pemesanan.DetailTransaksiActivity
import com.mediatama.travelorder.Pemesanan.Model.SudahSelesai
import com.mediatama.travelorder.databinding.RowSudahSelesaiBinding

class SudahSelesaiAdapter(context: Context?, databean: ArrayList<SudahSelesai.DATABean>) : RecyclerView.Adapter<SudahSelesaiAdapter.viewHolder>() {
    private var mContext : Context = context!!
    private var listSudah = databean


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SudahSelesaiAdapter.viewHolder {
        val view = RowSudahSelesaiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: SudahSelesaiAdapter.viewHolder, position: Int) {
        val data = listSudah[position]
        val tgl = data.tgl_pergi.substring(8,10)
        val bln = data.tgl_pergi.substring(5,7)
        val thn = data.tgl_pergi.substring(0,4)

        holder.binding.ruteSudah.text = data.rute_awal +"-"+ data.rute_tujuan
        holder.binding.mobilSudah.text = data.mobil
        holder.binding.tglFromSudah.text = "$tgl-$bln-$thn"

        holder.binding.seeDetailTransaksi.setOnClickListener {
            val intent = Intent(mContext,DetailTransaksiActivity::class.java)
            intent.putExtra("id", data.id_pemesanan)
            intent.putExtra("rute1", data.rute_awal)
            intent.putExtra("rute2", data.rute_tujuan)
            intent.putExtra("tarif", data.tarif)
            intent.putExtra("from", data.tgl_pergi)
            intent.putExtra("jumlah", data.jml_pesan)
//            intent.putExtra("invoice", data.invoice)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return listSudah.size
    }

    class viewHolder(var binding : RowSudahSelesaiBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}