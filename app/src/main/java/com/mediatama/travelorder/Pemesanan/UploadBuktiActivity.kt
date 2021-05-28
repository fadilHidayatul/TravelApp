package com.mediatama.travelorder.Pemesanan

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.loader.content.CursorLoader
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mediatama.travelorder.Pemesanan.Adapter.PhotoAdapter
import com.mediatama.travelorder.R
import com.mediatama.travelorder.UtilsApi.ApiClient
import com.mediatama.travelorder.databinding.ActivityUploadBuktiBinding
import com.tapadoo.alerter.Alerter
import dmax.dialog.SpotsDialog
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class UploadBuktiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUploadBuktiBinding
    private lateinit var context: Context
    var dialog: android.app.AlertDialog? = null

    private lateinit var adapterPhoto : PhotoAdapter

    private var REQUEST_PERMISSION_STORAGE : Int = 100
    private var REQUEST_PERMISSION_CAMERA : Int = 100
    private var REQUEST_TAKE_PICTURE_CODE : Int = 1
    private var REQUEST_GALLERY_PICTURE_CODE : Int = 2
    private var pathTakePhoto : String = ""
    private lateinit var uriGallery : Uri

    private var listImageUploaded : ArrayList<String>? = null
    private var imageSelected : ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBuktiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        dialog = SpotsDialog.Builder().setMessage("Please Wait").setCancelable(false).setContext(context).build()
        listImageUploaded = ArrayList()
        imageSelected = ArrayList()
        val intent = intent

        deskripsiPesanan(intent)

        if (ContextCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED){
            requestCameraPermission()
        }

        binding.btnUploadBuktiBayar.setOnClickListener {
            uploadImage()
        }

        binding.btnClearPhoto.setOnClickListener {
            imageSelected!!.clear()
            listImageUploaded!!.clear()
            adapterPhoto = PhotoAdapter(context, listImageUploaded!!)
            binding.recyclerPhoto.adapter = adapterPhoto
            binding.btnClearPhoto.visibility = View.GONE
        }

        binding.btnConfirmBayar.setOnClickListener {
            if (listImageUploaded!!.size == 0){
                Alerter.create(this)
                    .setTitle("Error")
                    .setText("Harap upload bukti pembayaran")
                    .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.red).show()
            }else{
                successUpload()
            }

        }

    }

    private fun deskripsiPesanan(intent: Intent) {
        val localeID = Locale("in", "ID")
        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)

        Glide.with(context)
            .load(ApiClient.MOBIL_IMG_URL+intent.getStringExtra("foto"))
            .fitCenter()
            .placeholder(R.color.black70)
            .into(binding.imgKendaraan)
        binding.ruteUpload.text = "Rute "+intent.getStringExtra("rute1") +" "+intent.getStringExtra("rute2")
        binding.mobilUpload.text = intent.getStringExtra("mobil")
        binding.tarifUpload.text = formatRupiah.format(intent.getStringExtra("tarif")!!.toDouble())
        binding.tglFromUpload.text = intent.getStringExtra("from")
        binding.tglToUpload.text = intent.getStringExtra("to")
    }

    private fun uploadImage() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED  ){
            requestStoragePermission()
        }

        if (listImageUploaded!!.size < 1){
            openFileimage()
        }else{
            Alerter.create(this)
                .setText("Bukti yang diupload hanya boleh satu")
                .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.red).show()
        }

    }

    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION_STORAGE
            )
        }else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION_STORAGE
            )
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA,
            )){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                REQUEST_PERMISSION_CAMERA
            )
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                REQUEST_PERMISSION_CAMERA
            )
        }

    }

    private fun openFileimage() {
        val option  = arrayOf<CharSequence>("Take Photo", "Choose From Gallery", "Cancel")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Upload Bukti Pembayaran")
        builder.setItems(option, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, item: Int) {
                if (option[item] == "Take Photo") {
                    val intentTakePicture = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                    if (intentTakePicture.resolveActivity(packageManager) != null) {
                        var imageFile: File? = null
                        try {
                            imageFile = getImageFile()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                        if (imageFile != null) {
                            var imageUri: Uri = FileProvider.getUriForFile(
                                context,
                                "com.mediatama.android.fileprovider",
                                imageFile
                            )
                            intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                            startActivityForResult(intentTakePicture, REQUEST_TAKE_PICTURE_CODE)
                        }
                    }

                } else if (option[item] == "Choose From Gallery") {
                    val intentPhotoGallery = Intent(Intent.ACTION_PICK)
                    intentPhotoGallery.type = "image/*"
                    startActivityForResult(intentPhotoGallery, REQUEST_GALLERY_PICTURE_CODE)

                } else if (option[item] == "Cancel") {
                    dialog!!.dismiss()
                }
            }
        })
        builder.show()
    }

    @Throws(IOException::class)
    private fun getImageFile(): File? {
        var timeStamp : String = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
        var imageName : String = "jpg_$timeStamp"

        var storageDirectory : File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        var imageFile = File.createTempFile(imageName, ".jpg", storageDirectory)
        pathTakePhoto = imageFile.absolutePath

        return imageFile
    }

    private fun getRealPathFromUri(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(applicationContext, uri, projection, null, null, null)
        val cursor : Cursor? = loader.loadInBackground()
        var column_index : Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

        cursor.moveToFirst()
        var result = cursor.getString(column_index)
        cursor.close()

        return result
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_GALLERY_PICTURE_CODE && resultCode == RESULT_OK){
            uriGallery = data!!.data!!

            Toast.makeText(context, getRealPathFromUri(uriGallery), Toast.LENGTH_SHORT).show()

            listImageUploaded!!.add(uriGallery.toString())
            adapterPhoto = PhotoAdapter(context, listImageUploaded!!)
            binding.recyclerPhoto.adapter = adapterPhoto
            binding.recyclerPhoto.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            binding.btnClearPhoto.visibility = View.VISIBLE

            imageSelected?.add(getRealPathFromUri(uriGallery).toString())

        }else if (requestCode == REQUEST_TAKE_PICTURE_CODE && resultCode == RESULT_OK){
            Toast.makeText(context, pathTakePhoto, Toast.LENGTH_LONG).show()

            listImageUploaded?.add(Uri.fromFile(File(pathTakePhoto)).toString())
            adapterPhoto = PhotoAdapter(context, listImageUploaded!!)
            binding.recyclerPhoto.adapter = adapterPhoto
            binding.recyclerPhoto.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            binding.btnClearPhoto.visibility = View.VISIBLE

            imageSelected!!.add(pathTakePhoto)
        }
    }

    private fun successUpload() {
        var imagePart = arrayOfNulls<MultipartBody.Part>(imageSelected!!.size)
        for (i in 0 until imageSelected!!.size){
            var file = File(imageSelected!![i])
            var propertyImage = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            imagePart[i] = MultipartBody.Part.createFormData("image[]", file.name, propertyImage)
        }
        dialog!!.show()
        ApiClient.getClient.uploadBuktiBayar(imagePart).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    dialog!!.dismiss()
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200") {
                        val intent = Intent(applicationContext, SuccessUploadActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(context, jsonO.getString("status"), Toast.LENGTH_SHORT)
                            .show()
                    }

                } else {
                    dialog!!.dismiss()
                    Toast.makeText(context, "Respon Failure", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                dialog!!.dismiss()
                Toast.makeText(context, "Koneksi Internet", Toast.LENGTH_SHORT).show()
            }

        })


    }

    fun finishUpload(view: View) {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog!!.dismiss()
    }
}
