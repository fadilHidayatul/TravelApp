package com.mediatama.travelorder.Pemesanan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mediatama.travelorder.Pemesanan.Adapter.PemesananFragmentAdapter
import com.mediatama.travelorder.databinding.FragmentPemesananBinding

/**
 * A simple [Fragment] subclass.
 */
class PemesananFragment : Fragment() {
    private lateinit var binding : FragmentPemesananBinding

    fun PemesananFragment(){

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPemesananBinding.inflate(inflater, container, false)

        binding.tabLayout.setupWithViewPager(binding.viewPager)

        val fragmentAdapter = PemesananFragmentAdapter(requireActivity().supportFragmentManager)
        fragmentAdapter.addFragment(BelumBayarFragment(),"Belum Bayar")
        fragmentAdapter.addFragment(SudahSelesaiFragment(),"Sudah Bayar")
        binding.viewPager.adapter = fragmentAdapter


        return binding.root
    }


}
