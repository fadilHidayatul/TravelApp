package com.mediatama.travelorder.Pemesanan

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.loader.content.CursorLoader
import androidx.recyclerview.widget.LinearLayoutManager
import com.mediatama.travelorder.Pemesanan.Adapter.PhotoAdapter
import com.mediatama.travelorder.R
import com.mediatama.travelorder.databinding.ActivityUploadBuktiBinding
import com.tapadoo.alerter.Alerter
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class UploadBuktiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUploadBuktiBinding
    private lateinit var context: Context

    private lateinit var adapterPhoto : PhotoAdapter

    private var REQUEST_TAKE_PICTURE_CODE : Int = 1
    private var REQUEST_GALLERY_PICTURE_CODE : Int = 2
    private var pathTakePhoto : String = ""
    private lateinit var uriGallery : Uri

    private var listImageUploaded : ArrayList<String>? = null
//    private var listImageUploaded = arrayListOf<String>()
    private var imageSelected : ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBuktiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        listImageUploaded = ArrayList()
        imageSelected = ArrayList()

        binding.btnUploadBuktiBayar.setOnClickListener {
            uploadImage()
        }
        binding.btnClearPhoto.setOnClickListener {
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

    private fun uploadImage() {
        if (listImageUploaded!!.size < 1){
            openFileimage()
        }else{
            Alerter.create(this)
                .setText("Bukti yang diupload hanya boleh satu")
                .setIcon(R.drawable.ic_warning).setBackgroundColorRes(R.color.red).show()
        }


    }

    private fun openFileimage() {
        val option  = arrayOf<CharSequence>("Take Photo", "Choose From Gallery", "Cancel")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Upload Bukti Pembayaran")
        builder.setItems(option, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, item: Int) {
                if (option[item] == "Take Photo") {
                    val intentTakePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
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

                }else if (option[item] == "Choose From Gallery") {
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
        var timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
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
            Toast.makeText(context, pathTakePhoto, Toast.LENGTH_SHORT).show()

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
        val intent = Intent(applicationContext, SuccessUploadActivity::class.java)
        startActivity(intent)
    }

    fun finishUpload(view: View) {
        finish()
    }
}
