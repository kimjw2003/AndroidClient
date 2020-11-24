package com.example.androidclient.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {

    val PREFS_FILENAME = "prefs"
    val prefs: SharedPreferences? = context.getSharedPreferences(PREFS_FILENAME, 0)

    fun getData(defValue: String): String {
        return prefs!!.getString("date", defValue).toString()
    }

    fun setDate(string: String) {
        prefs!!.edit().putString("date", string).apply()
    }

}