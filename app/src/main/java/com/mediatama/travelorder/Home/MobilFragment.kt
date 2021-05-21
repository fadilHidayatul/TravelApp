package com.mediatama.travelorder.Home

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mediatama.travelorder.Home.Adapter.MobilAdapter

import com.mediatama.travelorder.databinding.FragmentMobilBinding

/**
 * A simple [Fragment] subclass.
 */
class MobilFragment : DialogFragment() {
    private lateinit var binding : FragmentMobilBinding
    private lateinit var adapter : MobilAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMobilBinding.inflate(inflater,container,false)
        setLayout()
        getDataMobil()

        return binding.root
    }

    private fun getDataMobil() {
        adapter = MobilAdapter(context)
        binding.recyclerMobil.adapter = adapter
        binding.recyclerMobil.layoutManager = LinearLayoutManager(context)
        binding.recyclerMobil.setHasFixedSize(true)
    }

    private fun setLayout() {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthLcl = (displayMetrics.widthPixels * 0.9f).toInt()
        val heightLcl = (displayMetrics.heightPixels * 0.7f).toInt()

        val paramsLcl = binding.LayoutMobil.layoutParams
        paramsLcl.width = widthLcl
        paramsLcl.height = heightLcl
        binding.LayoutMobil.layoutParams = paramsLcl
    }

}
