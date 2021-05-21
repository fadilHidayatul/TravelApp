package com.mediatama.travelorder.LoginRegister

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mediatama.travelorder.R
import com.mediatama.travelorder.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var context: Context

    lateinit var gender : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        binding.genderLaki.setOnClickListener(){
            binding.genderLaki.borderWidth = 6
            binding.genderPerempuan.borderWidth = 0
            gender = "Laki-Laki"
        }
        binding.genderPerempuan.setOnClickListener {
            binding.genderLaki.borderWidth = 0
            binding.genderPerempuan.borderWidth = 6
            gender = "Perempuan"
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(context, SuccessRegisterActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(context,gender,Toast.LENGTH_SHORT).show()

        }

    }

    fun goLogin(view: View) {
        finish()
    }
}
