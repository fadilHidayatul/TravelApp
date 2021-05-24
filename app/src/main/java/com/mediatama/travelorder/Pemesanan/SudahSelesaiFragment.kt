package com.mediatama.travelorder.Pemesanan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mediatama.travelorder.Pemesanan.Adapter.SudahSelesaiAdapter

import com.mediatama.travelorder.R
import com.mediatama.travelorder.databinding.FragmentSudahSelesaiBinding

/**
 * A simple [Fragment] subclass.
 */
class SudahSelesaiFragment : Fragment() {
    private lateinit var binding : FragmentSudahSelesaiBinding
    private lateinit var adapter : SudahSelesaiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSudahSelesaiBinding.inflate(inflater,container,false)
        showListSudahBayar()
        return binding.root
    }

    private fun showListSudahBayar() {
        adapter = SudahSelesaiAdapter(context)
        binding.recyclerSudahBayar.adapter = adapter
        binding.recyclerSudahBayar.layoutManager = LinearLayoutManager(context)
        binding.recyclerSudahBayar.setHasFixedSize(true)
    }

}
