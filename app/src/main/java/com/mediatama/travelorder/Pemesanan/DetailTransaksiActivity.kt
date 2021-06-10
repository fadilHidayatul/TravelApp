package com.mediatama.travelorder.Pemesanan

import android.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.UtilsApi.ApiClient
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
        downloadInvoice()
    }

    private fun downloadInvoice() {
        val invoice = intent.getStringExtra("id")
        val pdf = ApiClient.PDF_URL+invoice

        binding.downloadInvoice.setOnClickListener {
            if (invoice == ""){
                Snackbar.make(findViewById(R.id.content),"Invoice kosong", Snackbar.LENGTH_LONG).show()
            }else{
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(pdf)
                startActivity(i)
            }
        }

    }

    private fun getDetailTransaksi(intent: Intent) {
        val localeID = Locale("in", "ID")
        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)

        val tglpesan : String = intent.getStringExtra("from")!!
        val tgl : String = intent.getStringExtra("from")!!.substring(8, 10)
        val bln : String = intent.getStringExtra("from")!!.substring(5, 7)
        val thn : String = intent.getStringExtra("from")!!.substring(0, 4)

        binding.idPesanan.text = intent.getStringExtra("id")+"-"+tglpesan.replace("-", "")
        binding.pemesan.text = manager.getNama()
        binding.rute.text = intent.getStringExtra("rute1")+" - "+intent.getStringExtra("rute2")
        binding.tarif.text = formatRupiah.format(intent.getStringExtra("tarif")!!.toDouble())
        binding.tglPergi.text = "$tgl-$bln-$thn"
        binding.jumlahTiketSudah.text = intent.getStringExtra("jumlah") + " Tiket"
    }

    fun finishDetail(view: View) {
        finish()
    }
}
