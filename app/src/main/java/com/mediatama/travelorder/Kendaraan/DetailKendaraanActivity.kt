package com.mediatama.travelorder.Kendaraan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mediatama.travelorder.R
import com.mediatama.travelorder.UtilsApi.ApiClient
import com.mediatama.travelorder.databinding.ActivityDetailKendaraanBinding


class DetailKendaraanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailKendaraanBinding
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKendaraanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        val intent = intent
       getData(intent)

        binding.backActivity.setOnClickListener {
            finish()
        }
    }

    private fun getData(intent: Intent) {
        Glide.with(context)
            .load(ApiClient.MOBIL_IMG_URL+intent.getStringExtra("foto"))
            .placeholder(R.color.black70)
            .into(binding.imgDetailKendaraan)

        binding.kursiDetail.text = intent.getStringExtra("kursi")+" seat"
        binding.transmisiDetail.text = intent.getStringExtra("transmisi")
        binding.tahunDetail.text = "Tahun "+intent.getStringExtra("tahun")
        binding.platDetail.text = intent.getStringExtra("plat")
        binding.detailNamaMobil.text = intent.getStringExtra("nama")
        binding.detailDeskripsiMobil.text = Html.fromHtml(intent.getStringExtra("desc")).toString().replace("\n", "").trim()

    }

}
