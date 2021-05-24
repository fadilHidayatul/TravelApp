package com.mediatama.travelorder.Pemesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mediatama.travelorder.MainActivity
import com.mediatama.travelorder.databinding.ActivitySuccessUploadBinding

class SuccessUploadActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySuccessUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackHome.setOnClickListener {
            var intent = Intent(applicationContext,MainActivity::class.java)
            intent.putExtra("FLAGPAGE",3)
            startActivity(intent)
        }
    }
}
