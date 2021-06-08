package com.mediatama.travelorder.SharedPreferences



import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {
    var SP : SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var mcontext: Context? = null

    var PRIVATE_MODE : Int = 0
    val PREF_MANAGER : String = "TRAVEL"
    val SESSION_KEY : String = "KEY"

    val IDRUTE : String = "0"
    val RUTE : String = "PADANG"
    val GETRUTE : String = "GETRUTE"
    val IDMOBIL : String = "00"
    val MOBIL : String = "BRIO"
    val GETMOBIL : String = "GETMOBIL"
    val KAPASITAS : String = "000"

    val IDUSER : String = "ID"
    val USERNAME : String = "UNAME"
    val NAMA : String = "NAMA"
    val JEKEL : String = "JEKEL"
    val HP : String = "HP"
    val TOKEN : String = "TOKEN"


    //initialize Shared Preferences
    init {
        mcontext = context
        SP = mcontext!!.getSharedPreferences(PREF_MANAGER, PRIVATE_MODE)
        editor = SP?.edit()
    }


    //Punya Rute
    fun setRuteBoolean() {
        editor!!.putBoolean(GETRUTE, true)
        editor!!.commit()
    }
    fun getRuteBoleean(): Boolean {
        return SP!!.getBoolean(GETRUTE, false)
    }
    fun removeRuteBoolean(){
        editor!!.putBoolean(GETRUTE,false)
        editor!!.commit()
    }
    fun setRute(key: String, value: String){
        editor!!.putString(key, value)
        editor!!.commit()
    }
    fun getRute() : String? {
        return SP!!.getString(RUTE, "")
    }
    fun setIdRute(key: String?, value: String?){
        editor!!.putString(key,value)
        editor!!.commit()
    }
    fun getIdRute() : String? {
        return SP!!.getString(IDRUTE,"")
    }

    //Punya Mobil
    fun setMobilBoolean(){
        editor!!.putBoolean(GETMOBIL,true)
        editor!!.commit()
    }
    fun getMobilBoolean() : Boolean {
        return SP!!.getBoolean(GETMOBIL,false)
    }
    fun removeMobilBoolean(){
        editor!!.putBoolean(GETMOBIL,false)
        editor!!.commit()
    }
    fun setMobil(key: String?, value: String?){
        editor?.putString(key,value)
        editor?.commit()
    }
    fun getMobil() : String? {
        return SP!!.getString(MOBIL, "")
    }
    fun setIdMobil(key: String?,value: String?){
        editor!!.putString(key,value)
        editor!!.commit()
    }
    fun getIdMobil() : String {
        return SP!!.getString(IDMOBIL, "")!!
    }
    fun setKapasitasMobil(key: String?,value: String?){
        editor!!.putString(key,value)
        editor!!.commit()
    }
    fun getKapasitasMobil() : String {
        return SP!!.getString(KAPASITAS, "")!!
    }

    //Punya User
    fun saveSession(){
        editor?.putBoolean(SESSION_KEY,true)
        editor?.commit()
    }
    fun getSession() : Boolean{
        return SP!!.getBoolean(SESSION_KEY,false)
    }
    fun removeSession(){
        editor?.putBoolean(SESSION_KEY,false)
        editor?.commit()
    }

    fun setID(key: String?,value: String?){
        editor?.putString(key,value)
        editor?.commit()
    }
    fun getID() : String {
        return SP!!.getString(IDUSER,"")!!
    }

    fun setUsername(key: String?, value: String?){
        editor?.putString(key,value)
        editor?.commit()
    }
    fun getUsername() : String{
        return SP!!.getString(USERNAME,"")!!
    }

    fun setNama(key: String?, value: String?){
        editor?.putString(key,value)
        editor?.commit()
    }
    fun getNama() : String {
        return SP!!.getString(NAMA,"")!!
    }

    fun setJekel(key: String?, value: String?){
        editor?.putString(key,value)
        editor?.commit()
    }
    fun getJekel() : String {
        return SP?.getString(JEKEL,"")!!
    }

    fun setHp(key: String?,value: String?){
        editor!!.putString(key,value)
        editor!!.commit()
    }
    fun getHp() : String {
        return SP!!.getString(HP,"")!!
    }

    fun setToken(key: String?,value: String?){
        editor?.putString(key,value)
        editor?.commit()
    }
    fun getToken() : String {
        return SP!!.getString(TOKEN,"")!!
    }



}

private fun SharedPreferences.Editor.putFloat(key: String?, value: Double?) {

}

