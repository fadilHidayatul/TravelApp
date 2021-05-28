package com.mediatama.travelorder.Home.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.mediatama.travelorder.Home.Model.SelectKendaraan
import com.mediatama.travelorder.MainActivity
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.databinding.RowMobilBinding

class MobilAdapter(context: Context?, databean: ArrayList<SelectKendaraan.DATABean>) : RecyclerView.Adapter<MobilAdapter.viewHolder>() {
    private var mContext: Context = context!!
    private var listMobil = databean
    lateinit var manager : PrefManager


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobilAdapter.viewHolder {
        val view : RowMobilBinding = RowMobilBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: MobilAdapter.viewHolder, position: Int) {
        manager = PrefManager(mContext)

        holder.binding.txtMobil.text = listMobil[position].nama_kendaraan
        holder.binding.txtMobil.setOnClickListener {
            manager.setMobilBoolean()
            manager.setMobil(manager.MOBIL,holder.binding.txtMobil.text.toString())
            manager.setIdMobil(manager.IDMOBIL, listMobil[position].id_kendaraan)

            var intent : Intent = Intent(mContext,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            mContext.startActivity(intent)

            (mContext as MainActivity).supportFragmentManager.findFragmentByTag("Mobil Dialog").let {
                (it as DialogFragment).dismiss()
            }
        }


    }

    override fun getItemCount(): Int {
        return listMobil.size
    }

    class viewHolder(var binding : RowMobilBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}