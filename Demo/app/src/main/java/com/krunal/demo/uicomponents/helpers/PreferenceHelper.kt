package com.krunal.demo.uicomponents.helpers

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object PreferenceHelper {

    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    /**
     * set the context that is being used to access the shared preferences
     */
    fun initialize(context: Context) {
        preferences = getDefaultSharedPreferences(context)
        editor = preferences.edit()
    }

    fun putString(key: String, value: String) {
        editor.putString(key, value).commit()
    }

    fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).commit()
    }

    fun putInt(key: String, value: Int) {
        editor.putInt(key, value).commit()
    }

    fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value).commit()
    }

    fun putLong(key: String, value: Long) {
        editor.putLong(key, value).commit()
    }

    fun getString(key: String?, defValue: String): String {
        return preferences.getString(key, defValue) ?: defValue
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    fun getInt(key: String?, defValue: Int): Int {
        return runCatching {
            preferences.getInt(key, defValue)
        }.getOrElse { preferences.getLong(key, defValue.toLong()).toInt() }
    }

    fun getLong(key: String?, defValue: Long): Long {
        return preferences.getLong(key, defValue)
    }

    fun getFloat(key: String?, defValue: Float): Float {
        return preferences.getFloat(key, defValue)
    }

    fun clearPreferences() {
        editor.clear().apply()
    }

    private fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}