package com.example.marvel.data.local

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.marvel.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppData @Inject constructor(@ApplicationContext context: Context) {

    private val path: String = context.getString(R.string.user_data)
    private val prefs: SharedPreferences = context.getSharedPreferences(path, Activity.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun getData(key: String): String? {
        return prefs.getString(key, "")
    }

    fun clearData() {
        prefs.edit().clear().apply()
    }
}