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

    val RUTE : String = "PADANG"
    val GETRUTE : String = "GETRUTE"
    val MOBIL : String = "BRIO"
    val GETMOBIL : String = "GETMOBIL"

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






}

private fun SharedPreferences.Editor.putFloat(key: String?, value: Double?) {

}

