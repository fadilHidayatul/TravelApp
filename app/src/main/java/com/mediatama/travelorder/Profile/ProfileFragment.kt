package com.mediatama.travelorder.Profile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.crowdfire.cfalertdialog.CFAlertDialog
import com.google.android.material.snackbar.Snackbar
import com.mediatama.travelorder.LoginRegister.LoginActivity
import com.mediatama.travelorder.R
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.UtilsApi.ApiClient
import com.mediatama.travelorder.databinding.FragmentProfileBinding
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
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var manager : PrefManager
    private lateinit var builder : CFAlertDialog.Builder

    private var CAMERA_CODE : Int = 100
    private var TAKE_PICTURE_CODE : Int = 1
    private var GALLERY_CODE : Int = 2
    private var pathTakePhoto : String = ""
    private lateinit var uriGalery : Uri

    private lateinit var imageChosen : String
    private lateinit var urlGambar : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        manager = PrefManager(requireContext())

        getPermission()
        fetchProfile()

        binding.changePicture.setOnClickListener {
            openFileImage()
        }

        initialDialog()
        doLogout()

        return binding.root
    }

    private fun getPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED ){
            requestCameraPermission()
        }
    }
    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),android.Manifest.permission.CAMERA )){
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.READ_EXTERNAL_STORAGE),
                CAMERA_CODE
            )
        }else{
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.READ_EXTERNAL_STORAGE),
                CAMERA_CODE
            )
        }
    }

    private fun fetchProfile() {
        binding.namaProfil.text = manager.getNama()
        binding.noProfil.text = manager.getHp()
        binding.genderProfil.text = manager.getJekel()
        binding.usernameProfil.text = manager.getUsername()

        ApiClient.getClient.pesananSudahBayar(manager.getID()).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200") {
                        val jsonA = jsonO.getJSONArray("DATA")

                        binding.jumlahPesanan.text = jsonA.length().toString() + " Pemesanan"
                    }else{
                        binding.jumlahPesanan.text =  "0 Pemesanan"
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context,t.localizedMessage,Toast.LENGTH_LONG).show()
            }
        })

        ApiClient.getClient.getProfilPic(manager.getID()).enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        val data = jsonO.getJSONObject("data")
                        urlGambar = data.getString("foto_profil")

                        Glide.with(requireContext())
                            .load(ApiClient.PROFIL_URL+urlGambar)
                            .placeholder(R.color.black70)
                            .into(binding.imgProfil)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun openFileImage() {
        var option = arrayOf<CharSequence>("Take Picture","Choose From Gallery","Cancel")
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Ganti Foto Profil")
        builder.setItems(option, object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                when {
                    option[p1] == "Take Picture" -> {
                        val intentTakePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        if (intentTakePicture.resolveActivity(activity!!.packageManager) != null){
                            var imageFile : File? = null
                            try {
                                imageFile = getImageFile()
                            }catch (e: IOException){
                                e.printStackTrace()
                            }

                            if (imageFile != null){
                                val imageUri : Uri = FileProvider.getUriForFile(
                                    requireContext(),
                                    "com.mediatama.android.fileprovider",
                                    imageFile
                                )
                                intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                                startActivityForResult(intentTakePicture,TAKE_PICTURE_CODE)
                            }
                        }

                    }
                    option[p1] == "Choose From Gallery" -> {
                        val intentGallery = Intent(Intent.ACTION_PICK)
                        intentGallery.type = "image/*"
                        startActivityForResult(intentGallery,GALLERY_CODE)
                    }
                    else -> {
                        p0!!.dismiss()
                    }
                }
            }

        })

        builder.show()
    }

    @Throws(IOException::class)
    private fun getImageFile(): File? {
        val timeStamp : String = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
        val imageName = "jpg_$timeStamp"

        val storageDirectory : File = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val imageFile = File.createTempFile(imageName, ".jpg", storageDirectory)
        pathTakePhoto = imageFile.absolutePath

        return imageFile
    }
    private fun getRealPathFromUri(uri : Uri) : String{
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(requireContext(),uri,projection,null,null,null)
        val cursor : Cursor? = loader.loadInBackground()
        val clm_id : Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

        cursor.moveToFirst()
        val result = cursor.getString(clm_id)
        cursor.close()

        return result
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_CODE && resultCode == AppCompatActivity.RESULT_OK){
            uriGalery = data!!.data!!
//            Toast.makeText(requireContext(),getRealPathFromUri(uriGalery),Toast.LENGTH_SHORT).show()

            val uriImage = Uri.parse(uriGalery.toString())
            binding.imgProfil.setImageURI(uriImage)

            imageChosen = getRealPathFromUri(uriGalery)
            sendChangeProfilPicToDatabase()
        }else if (requestCode == TAKE_PICTURE_CODE && resultCode == AppCompatActivity.RESULT_OK){
//            Toast.makeText(requireContext(),pathTakePhoto,Toast.LENGTH_SHORT).show()

            val uriImage = Uri.fromFile(File(pathTakePhoto))
            binding.imgProfil.setImageURI(uriImage)
            binding.imgProfil.rotation = 90F

            imageChosen = pathTakePhoto
            sendChangeProfilPicToDatabase()
        }
    }

    private fun sendChangeProfilPicToDatabase() {
        val imagePart: MultipartBody.Part

        val file = File(imageChosen)
        val propertyImage = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        imagePart = MultipartBody.Part.createFormData("fotoprofil",file.name,propertyImage)

        ApiClient.getClient.changeProfilPic(
            manager.getID().toInt(),
            imagePart
        ).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val jsonO = JSONObject(response.body()!!.string())
                    if (jsonO.getString("status") == "200"){
                        Toast.makeText(requireContext(),jsonO.getString("message"), Toast.LENGTH_SHORT).show()
                    }else{
                        Snackbar.make(view!!,jsonO.getString("message"),Snackbar.LENGTH_LONG).show()

                        Glide.with(requireContext())
                            .load(ApiClient.PROFIL_URL+urlGambar)
                            .placeholder(R.color.black70)
                            .into(binding.imgProfil)

                    }
                }else{
                    Toast.makeText(requireContext(),"Respon Failure", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(requireContext(),t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

    }


    private fun initialDialog(){

        builder = CFAlertDialog.Builder(requireContext())
            .setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET)
            .setTitle("LOGOUT")
            .setMessage("Apakah anda akan keluar dari aplikasi? Anda akan login ulang kembali")
            .addButton(
                "Keluar",
                -1,
                -1,
                CFAlertDialog.CFAlertActionStyle.NEGATIVE,
                CFAlertDialog.CFAlertActionAlignment.JUSTIFIED,
                object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()
                        manager.removeSession()
                        manager.removeRuteBoolean()
                        manager.removeMobilBoolean()
                        activity!!.finish()

                        val intent = Intent(requireContext(), LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }
                })
            .addButton(
                "Tidak",
                Color.parseColor("#66040404"),
                Color.parseColor("#FFFFFF"),
                CFAlertDialog.CFAlertActionStyle.DEFAULT,
                CFAlertDialog.CFAlertActionAlignment.JUSTIFIED,
                object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()
                    }
                })
    }


    private fun doLogout() {
        binding.logout.setOnClickListener(object : View.OnClickListener {
            @SuppressLint("ResourceAsColor")
            override fun onClick(v: View?) {
                builder.show()
            }

        })
    }


}