package com.mediatama.travelorder.Home.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.mediatama.travelorder.Home.Model.Rute
import com.mediatama.travelorder.MainActivity
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.databinding.RowRuteBinding


class RuteAdapter(context: Context?, databean: ArrayList<Rute.DATABean>) : RecyclerView.Adapter<RuteAdapter.viewHolder>() {
    private var mContext: Context = context!!
    private lateinit var manager : PrefManager

    private var listRute = databean


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuteAdapter.viewHolder {
        val view : RowRuteBinding = RowRuteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: RuteAdapter.viewHolder, position: Int) {
        manager = PrefManager(mContext)

        holder.binding.txtRute.text = listRute[position].rute_awal + "-" + listRute[position].rute_tujuan

        holder.binding.txtRute.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                manager.setRuteBoolean()
                manager.setIdRute(manager.IDRUTE, listRute[position].id_rute)
                manager.setRute(manager.RUTE, holder.binding.txtRute.text.toString())

                val intent = Intent(mContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                mContext.startActivity(intent)

                (mContext as MainActivity).supportFragmentManager.findFragmentByTag("Rute Dialog")
                    ?.let {
                        (it as DialogFragment).dismiss()
                    }//close dialog


            }

        })
    }

    override fun getItemCount(): Int {
        return listRute.size
    }

    class viewHolder(val binding: RowRuteBinding) : RecyclerView.ViewHolder(binding.root)
}