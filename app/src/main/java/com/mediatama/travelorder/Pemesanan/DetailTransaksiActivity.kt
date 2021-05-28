package com.mediatama.travelorder.Pemesanan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.databinding.ActivityDetailTransaksiBinding
import java.text.NumberFormat
import java.util.*

class DetailTransaksiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailTransaksiBinding
    private lateinit var context: Context
    private lateinit var manager : PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        manager = PrefManager(context)

        val intent = intent
        getDetailTransaksi(intent)


    }

    private fun getDetailTransaksi(intent: Intent) {
        val localeID = Locale("in", "ID")
        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)

        val tglpesan : String = intent.getStringExtra("pesan")!!


        binding.idPesanan.text = intent.getStringExtra("id")+"-"+tglpesan.replace("-", "")
        binding.pemesan.text = manager.getNama()
        binding.rute.text = intent.getStringExtra("rute1")+" - "+intent.getStringExtra("rute2")

        binding.tarif.text = formatRupiah.format(intent.getStringExtra("tarif")!!.toDouble())

        binding.tglPergi.text = intent.getStringExtra("from")
        binding.tglKembali.text = intent.getStringExtra("to")
    }

    fun finishDetail(view: View) {
        finish()
    }
}
