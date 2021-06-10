package com.mediatama.travelorder.Pemesanan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mediatama.travelorder.MainActivity
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.databinding.ActivitySuccessUploadBinding

class SuccessUploadActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySuccessUploadBinding
    private lateinit var manager : PrefManager
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        manager = PrefManager(context)

        binding.username.text = manager.getNama()

        binding.btnBackHome.setOnClickListener {
            var intent = Intent(applicationContext,MainActivity::class.java)
            intent.putExtra("FLAGPAGE",3)
            startActivity(intent)
        }
    }
}
