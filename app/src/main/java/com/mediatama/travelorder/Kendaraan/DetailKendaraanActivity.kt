package com.mediatama.travelorder.Kendaraan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mediatama.travelorder.databinding.ActivityDetailKendaraanBinding

class DetailKendaraanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailKendaraanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKendaraanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backActivity.setOnClickListener {
            finish()
        }
    }
}
