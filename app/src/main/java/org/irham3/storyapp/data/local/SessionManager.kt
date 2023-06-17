package org.irham3.storyapp.data.local

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context) {
    private var sharedPref: SharedPreferences = context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPref.edit()

    fun createLoginSession() {
        editor.putBoolean(KEY_LOGIN_STATUS, true).commit()
    }

    fun clearLoginSession() {
        editor.putBoolean(KEY_LOGIN_STATUS, false).commit()
    }

    fun getLoginStatus() : Boolean = sharedPref.getBoolean(KEY_LOGIN_STATUS, false)

    fun saveToPreference(key: String, value: String) = editor.putString(key, value).commit()

    fun getFromPreference(key: String) = sharedPref.getString(key, "")

    companion object {
        const val KEY_LOGIN_STATUS = "isLogin"
        const val KEY_EMAIL = "email"
        const val KEY_NAME = "name"
        const val KEY_PASSWORD = "password"
    }
}