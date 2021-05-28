package com.mediatama.travelorder.LoginRegister

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.mediatama.travelorder.LoginRegister.Model.User
import com.mediatama.travelorder.MainActivity
import com.mediatama.travelorder.R
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.UtilsApi.ApiClient
import com.mediatama.travelorder.databinding.ActivityLoginBinding
import com.tapadoo.alerter.Alert
import com.tapadoo.alerter.Alerter
import dmax.dialog.SpotsDialog
import okhttp3.ResponseBody
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var context: Context
    private lateinit var manager : PrefManager

    private lateinit var dialog : SpotsDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        manager = PrefManager(this)

        dialog = SpotsDialog.Builder().setMessage("Harap Tunggu").setCancelable(false).setContext(context).build() as SpotsDialog

        binding.btnLogin.setOnClickListener {
            doLogin()
        }

    }

    private fun doLogin() {
        if (TextUtils.isEmpty(binding.loginUsername.text.toString())){
            Alerter.create(this)
                .setText("Isi username")
                .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
        }else if (TextUtils.isEmpty(binding.loginPassword.text.toString())){
            Alerter.create(this)
                .setText("Isi Password")
                .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.black).show()
        }else{
            sendDataLogin()
        }
    }

    private fun sendDataLogin() {
        dialog.show()
        ApiClient.getClient.login(
            binding.loginUsername.text.toString(),
            binding.loginPassword.text.toString()
        ).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val jsonO = JSONObject(response.body()!!.string());
                    if (jsonO.getString("status") == "200"){
                        dialog.dismiss()

                        val data = jsonO.getJSONObject("data")

                        val gson = Gson()
                        val user : User.DataBean = gson.fromJson(data.toString(), User.DataBean::class.java)

                        manager.saveSession()
                        manager.setID(manager.IDUSER,user.id_pelanggan)
                        manager.setUsername(manager.USERNAME, user.username)
                        manager.setNama(manager.NAMA, user.nama)
                        manager.setJekel(manager.JEKEL, user.jenis_kelamin)
                        manager.setHp(manager.HP, user.no_hp)
                        manager.setToken(manager.TOKEN, user.token)

                        val intent = Intent(context,MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }else{
                        dialog.dismiss()
                        Alerter.create(this@LoginActivity)
                            .setTitle("Error")
                            .setText(jsonO.getString("message"))
                            .setIcon(R.drawable.ic_warning)
                            .setBackgroundColorRes(R.color.black)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                dialog.dismiss()
                Alerter.create(this@LoginActivity)
                    .setTitle("Error")
                    .setText(t.localizedMessage)
                    .setIcon(R.drawable.ic_warning)
                    .setBackgroundColorRes(R.color.black)
                    .show()
            }

        })
    }

    fun goRegister(view: View) {
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        manager = PrefManager(this)
        val userId : Boolean = manager.getSession()

        if (userId){
            val intent = Intent(context,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.dismiss()
    }
}
