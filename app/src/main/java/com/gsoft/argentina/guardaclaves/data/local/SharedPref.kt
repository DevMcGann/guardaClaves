package com.gsoft.argentina.guardaclaves.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPref (context: Context) {

    private val dict = "guardaClaves"
    val sharedPref: SharedPreferences = context.getSharedPreferences(dict, Context.MODE_PRIVATE)

    fun savePref(key:String, value:String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor!!.commit()
    }

    fun saveFirst (key:String, value:Boolean){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value.toString())
        editor!!.commit()
    }


    fun getFromPrefs(key: String): String? {
        return sharedPref.getString(key, null)
    }



    fun clearSharedPreference() {
        sharedPref.edit().clear().commit()
    }

    fun removeValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(KEY_NAME)
        editor.commit()
    }


}