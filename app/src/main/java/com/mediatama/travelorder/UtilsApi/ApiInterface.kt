package com.mediatama.travelorder.UtilsApi

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @FormUrlEncoded
    @POST("user/register.php")
    fun register(
        @Field("nama") namaPengguna: String,
        @Field("jekel") jenisKelamin: String,
        @Field("hp") noHP: String,
        @Field("username") username: String,
        @Field("password") password: String
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("user/login.php")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Call<ResponseBody>

    @GET("pemesanan/show_mobil.php")
    fun getKendaraan(

    ) : Call<ResponseBody>

    @GET("pemesanan/show_rute.php")
    fun showRute(

    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("pemesanan/select_mobil.php")
    fun selectKendaraan(
        @Field("id_rute") idrute: String?
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("pemesanan/add_pesanan.php")
    fun addPesanan(
        @Field("pelanggan") idPelanggan: String?,
        @Field("rute") idRute: String?,
        @Field("mobil") idKendaraan: String?,
        @Field("tgl_from") from: String?,
        @Field("jumlah_kursi") jumlahKursi: String?
    ) : Call<ResponseBody>


    @FormUrlEncoded
    @POST("pemesanan/pesanan_belum_bayar.php")
    fun pesananBelumBayar(
        @Field("id_pelanggan") idPelanggan: String?
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("pemesanan/pesanan_sudah_bayar.php")
    fun pesananSudahBayar(
        @Field("id_pelanggan") idPelanggan: String?
    ) : Call<ResponseBody>

    @Multipart
    @POST("pemesanan/upload_bukti.php")
    fun uploadBuktiBayar(
        @Part("id_pemesanan") idpemesanan: Int,
        @Part filePart: Array<MultipartBody.Part?>?
    ) : Call<ResponseBody>

    @Multipart
    @POST("user/change_profil_pic.php")
    fun changeProfilPic(
        @Part("id_user") idUser : Int,
        @Part filePart : MultipartBody.Part?
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("user/load_profil_pic.php")
    fun getProfilPic(
        @Field("id_user") iduser : String
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("pemesanan/delete_pesanan.php")
    fun deletePesanan(
        @Field("idpesan") idPesan : String
    ) : Call<ResponseBody>




}