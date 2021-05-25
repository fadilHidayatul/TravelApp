package com.mediatama.travelorder.Pemesanan.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mediatama.travelorder.databinding.RowPhotoBinding

class PhotoAdapter(context: Context, listImageUploaded: ArrayList<String>) : RecyclerView.Adapter<PhotoAdapter.viewHolder>() {
    private var mContext : Context = context
    private var listImage : ArrayList<String>? = listImageUploaded


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.viewHolder {
        val view = RowPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.viewHolder, position: Int) {
        var uri = Uri.parse(listImage!![position])
        holder.binding.imgUploaded.setImageURI(uri)

    }

    override fun getItemCount(): Int {
        return listImage!!.size
    }

    class viewHolder(var binding: RowPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}