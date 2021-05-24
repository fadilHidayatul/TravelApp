package com.mediatama.travelorder.Pemesanan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mediatama.travelorder.Pemesanan.Adapter.BelumBayarAdapter

import com.mediatama.travelorder.databinding.FragmentBelumBayarBinding

/**
 * A simple [Fragment] subclass.
 */
class BelumBayarFragment : Fragment() {
    private lateinit var binding : FragmentBelumBayarBinding
    private lateinit var adapter : BelumBayarAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBelumBayarBinding.inflate(inflater,container,false)
        showListBelumBayar()
        return binding.root
    }

    private fun showListBelumBayar() {
        adapter = BelumBayarAdapter(context)
        binding.recyclerBelumBayar.adapter = adapter
        binding.recyclerBelumBayar.layoutManager = LinearLayoutManager(context)
        binding.recyclerBelumBayar.setHasFixedSize(true)
    }

}
