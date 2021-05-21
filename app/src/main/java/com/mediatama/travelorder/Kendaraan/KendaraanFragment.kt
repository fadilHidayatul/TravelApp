package com.mediatama.travelorder.Kendaraan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mediatama.travelorder.Kendaraan.Adapter.KendaraanAdapter

import com.mediatama.travelorder.databinding.FragmentKendaraanBinding

/**
 * A simple [Fragment] subclass.
 */
class KendaraanFragment : Fragment() {
    private lateinit var binding : FragmentKendaraanBinding

    private lateinit var adapter : KendaraanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKendaraanBinding.inflate(inflater,container,false)

        showKendaraan()

        return binding.root
    }

    private fun showKendaraan() {
        adapter = KendaraanAdapter(context)
        binding.recyclerKendaraan.adapter = adapter
        binding.recyclerKendaraan.layoutManager = LinearLayoutManager(context)
        binding.recyclerKendaraan.setHasFixedSize(true)
    }


}
