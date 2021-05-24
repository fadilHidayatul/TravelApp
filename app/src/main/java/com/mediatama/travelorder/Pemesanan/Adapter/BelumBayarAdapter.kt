package com.mediatama.travelorder.Pemesanan.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mediatama.travelorder.Pemesanan.UploadBuktiActivity
import com.mediatama.travelorder.databinding.RowBelumBayarBinding

class BelumBayarAdapter(context: Context?) : RecyclerView.Adapter<BelumBayarAdapter.viewHolder>() {
    private var mContext : Context = context!!


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BelumBayarAdapter.viewHolder {
        val view = RowBelumBayarBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: BelumBayarAdapter.viewHolder, position: Int) {
        holder.binding.ruteBelum.text = "Padang - Solok"
        holder.binding.uploadBukti.setOnClickListener {
            Toast.makeText(mContext,"$position",Toast.LENGTH_SHORT).show()
            val intent = Intent(mContext,UploadBuktiActivity::class.java)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return 8
    }

    class viewHolder(var binding : RowBelumBayarBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}