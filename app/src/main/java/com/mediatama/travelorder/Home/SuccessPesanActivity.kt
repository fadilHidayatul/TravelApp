package com.mediatama.travelorder.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mediatama.travelorder.MainActivity
import com.mediatama.travelorder.databinding.ActivitySuccessPesanBinding

class SuccessPesanActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySuccessPesanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessPesanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getintent : Intent = intent


        binding.btnBackHome.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}
