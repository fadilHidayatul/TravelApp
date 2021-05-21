package com.mediatama.travelorder.Pemesanan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mediatama.travelorder.R
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
        binding = FragmentPemesananBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


}
