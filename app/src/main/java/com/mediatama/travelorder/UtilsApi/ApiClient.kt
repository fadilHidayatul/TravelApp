package com.mediatama.travelorder.UtilsApi

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var BASE_URL : String = "http://192.168.100.35/travelapp/"      //localhost
    var MOBIL_IMG_URL : String = "http://192.168.100.35/travelapp/img/mobil/"
    var PDF_URL : String = "http://192.168.100.35/travelapp/pdf/"
    var PROFIL_URL : String = "http://192.168.100.35/travelapp/img/profil/"

//    var BASE_URL : String = "http://192.168.100.70/travelapp/"      //Takana
//    var MOBIL_IMG_URL : String = "http://192.168.100.70/travelapp/img/mobil/"

    val getClient : ApiInterface
        get() {
            val gson = GsonBuilder().setLenient().create()
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
}