package com.mediatama.travelorder.Pemesanan.Adapter

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.crowdfire.cfalertdialog.CFAlertDialog
import com.google.android.material.snackbar.Snackbar
import com.mediatama.travelorder.Pemesanan.Model.BelumBayar
import com.mediatama.travelorder.Pemesanan.UploadBuktiActivity
import com.mediatama.travelorder.UtilsApi.ApiClient
import com.mediatama.travelorder.databinding.RowBelumBayarBinding
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class BelumBayarAdapter(context: Context?, databean: ArrayList<BelumBayar.DATABean>) : RecyclerView.Adapter<BelumBayarAdapter.viewHolder>() {
    private var mContext : Context = context!!
    private var listBelumBayar = databean
    private lateinit var builder : CFAlertDialog.Builder

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

        holder.binding.hapusPesanan.setOnClickListener {
            ApiClient.getClient.deletePesanan(
                data.id_pemesanan
            ).enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful){
                        val jsonO = JSONObject(response.body()!!.string())
                        if (jsonO.getString("status") == "200"){
                            builder = CFAlertDialog.Builder(mContext)
                                .setDialogStyle(CFAlertDialog.CFAlertStyle.NOTIFICATION)
                                .setTitle("Hapus Pesanan")
                                .setMessage("Apakah Anda Akan Menghapus Pesanan Ini?")
                                .setCancelable(true)
                                .addButton("Ya",
                                    -1,
                                    -1,
                                    CFAlertDialog.CFAlertActionStyle.POSITIVE,
                                    CFAlertDialog.CFAlertActionAlignment.END,
                                    object : DialogInterface.OnClickListener{
                                        override fun onClick(p0: DialogInterface?, p1: Int) {
                                            p0!!.dismiss()
                                            holder.binding.rowPesanan.visibility = View.GONE
                                        }

                                    }
                                )

                            builder.show()

                        }else{
                            Toast.makeText(mContext,jsonO.getString("message"),Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(mContext,t.localizedMessage,Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    override fun getItemCount(): Int {
       return listBelumBayar.size
    }

    class viewHolder(var binding : RowBelumBayarBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}