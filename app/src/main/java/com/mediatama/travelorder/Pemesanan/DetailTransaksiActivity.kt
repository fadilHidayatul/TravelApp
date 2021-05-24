package com.mediatama.travelorder.Pemesanan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mediatama.travelorder.databinding.ActivityDetailTransaksiBinding

class DetailTransaksiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailTransaksiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun finishDetail(view: View) {
        finish()
    }
}
