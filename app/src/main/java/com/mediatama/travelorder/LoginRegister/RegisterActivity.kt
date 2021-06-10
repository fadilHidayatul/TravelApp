package com.mediatama.travelorder.LoginRegister

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.mediatama.travelorder.R
import com.mediatama.travelorder.UtilsApi.ApiClient
import com.mediatama.travelorder.databinding.ActivityRegisterBinding
import com.tapadoo.alerter.Alerter
import okhttp3.ResponseBody
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var context: Context

    var gender : String = ""


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
            doRegister()
        }

    }

    private fun doRegister() {

        if (TextUtils.isEmpty(binding.regNama.text.toString())){
            Alerter.create(this)
                .setText("Isi nama pengguna")
                .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
        }else if (TextUtils.isEmpty(binding.regHp.text.toString())){
            Alerter.create(this)
                .setText("Isi no HP")
                .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
        }else if (TextUtils.isEmpty(binding.regUsername.text.toString())){
            Alerter.create(this)
                .setText("Isi username")
                .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
        }else if (TextUtils.isEmpty(binding.regPassword.text.toString())){
            Alerter.create(this)
                .setText("Isi password")
                .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
        }else if ( gender == ""){
            Alerter.create(this)
                .setText("Pilih jenis kelamin")
                .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
        }else{
            sendDataRegister()
        }
    }

    private fun sendDataRegister() {
        ApiClient.getClient.register(
            binding.regNama.text.toString(),
            gender,
            binding.regHp.text.toString(),
            binding.regUsername.text.toString(),
            binding.regPassword.text.toString()
        ).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        val intent = Intent(context, SuccessRegisterActivity::class.java)
                        startActivity(intent)
                        finish()

                    }else{
                        Alerter.create(this@RegisterActivity)
                            .setText(jsonO.getString("message"))
                            .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Alerter.create(this@RegisterActivity)
                    .setText(t.localizedMessage)
                    .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
            }

        })
    }


    fun goLogin(view: View) {
        finish()
    }
}
