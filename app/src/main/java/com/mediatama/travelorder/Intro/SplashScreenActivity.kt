package com.mediatama.travelorder.Intro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mediatama.travelorder.LoginRegister.LoginActivity
import com.mediatama.travelorder.R

class SplashScreenActivity : AppCompatActivity() {
lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContentView(R.layout.activity_splash_screen)

        val intent = Intent(this,LoginActivity::class.java)
        var timer : Thread = object : Thread(){
            override fun run() {
                try {
                    sleep(3000)
                }catch (e: InterruptedException){
                    e.printStackTrace()
                }finally {
                    startActivity(intent)
                    finish()
                }
                super.run()
            }
        }

        timer.start()
    }
}
