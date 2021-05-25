package com.mediatama.travelorder.UtilsApi

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Multipart
    @POST("upload_photo.php")
    fun uploadBuktiBayar(
        @Part filePart : Array<MultipartBody.Part?>?
    ) : Call<ResponseBody>

}