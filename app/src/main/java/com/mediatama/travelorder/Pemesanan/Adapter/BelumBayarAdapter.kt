package com.mediatama.travelorder.Pemesanan.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mediatama.travelorder.Pemesanan.Model.BelumBayar
import com.mediatama.travelorder.Pemesanan.UploadBuktiActivity
import com.mediatama.travelorder.databinding.RowBelumBayarBinding
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class BelumBayarAdapter(context: Context?, databean: ArrayList<BelumBayar.DATABean>) : RecyclerView.Adapter<BelumBayarAdapter.viewHolder>() {
    private var mContext : Context = context!!
    private var listBelumBayar = databean


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BelumBayarAdapter.viewHolder {
        val view = RowBelumBayarBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: BelumBayarAdapter.viewHolder, position: Int) {
        val data = listBelumBayar[position]
        val localeID = Locale("in", "ID")
        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)

        holder.binding.ruteBelum.text = "${data.rute_awal} - ${data.rute_tujuan}"
        holder.binding.mobilBelum.text = data.mobil
        holder.binding.tarifBelum.text = formatRupiah.format(data.tarif.toDouble())

        holder.binding.uploadBukti.setOnClickListener {

            val intent = Intent(mContext,UploadBuktiActivity::class.java)
            intent.putExtra("idPesanan", data.id_pemesanan.toInt())
            intent.putExtra("foto", data.foto)
            intent.putExtra("rute1", data.rute_awal)
            intent.putExtra("rute2", data.rute_tujuan)
            intent.putExtra("mobil", data.mobil)
            intent.putExtra("tarif", data.tarif)
            intent.putExtra("pesan", data.jumlah_pesan)
            intent.putExtra("from", data.tgl_pergi)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return listBelumBayar.size
    }

    class viewHolder(var binding : RowBelumBayarBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}