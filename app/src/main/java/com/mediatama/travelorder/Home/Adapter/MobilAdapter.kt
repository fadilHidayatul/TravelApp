package com.mediatama.travelorder.Home.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.mediatama.travelorder.MainActivity
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.databinding.RowMobilBinding

class MobilAdapter(context: Context?) : RecyclerView.Adapter<MobilAdapter.viewHolder>() {
    private var mContext: Context = context!!
    lateinit var manager : PrefManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobilAdapter.viewHolder {
        val view : RowMobilBinding = RowMobilBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: MobilAdapter.viewHolder, position: Int) {
        manager = PrefManager(mContext)

        holder.binding.txtMobil.text = "Honda Brio"
        holder.binding.txtMobil.setOnClickListener {
            manager.setMobilBoolean()
            manager.setMobil(manager.MOBIL,holder.binding.txtMobil.text.toString())

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
        return 8
    }

    class viewHolder(var binding : RowMobilBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}