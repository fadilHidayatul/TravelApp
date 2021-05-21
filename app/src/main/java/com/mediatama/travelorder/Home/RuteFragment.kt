package com.mediatama.travelorder.Home

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mediatama.travelorder.Home.Adapter.RuteAdapter

import com.mediatama.travelorder.databinding.FragmentRuteBinding


/**
 * A simple [Fragment] subclass.
 */
class RuteFragment : DialogFragment() {
    private lateinit var binding : FragmentRuteBinding

    private lateinit var adapter : RuteAdapter
    //isilist


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRuteBinding.inflate(inflater,container,false)
        setLayout()
        showRute()

        return binding.root
    }

    private fun showRute() {
        adapter = RuteAdapter(context)
        binding.recyclerRute.adapter = adapter
        binding.recyclerRute.layoutManager = LinearLayoutManager(context)
        binding.recyclerRute.setHasFixedSize(true)
    }

    private fun setLayout() {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthLcl = (displayMetrics.widthPixels * 0.9f).toInt()
        val heightLcl = (displayMetrics.heightPixels * 0.7f).toInt()

        val paramsLcl = binding.tesLinear.layoutParams
        paramsLcl.width = widthLcl
        paramsLcl.height = heightLcl
        binding.tesLinear.layoutParams = paramsLcl
    }


}
