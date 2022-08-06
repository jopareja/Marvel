package com.example.marvel.data.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.marvel.data.repositories.LocalProvider
import com.example.marvel.domain.data.Character
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object LocalClient : LocalProvider {

    private lateinit var preferences: SharedPreferences
    private const val PREFERENCES_FILE_NAME = "PREFERENCES_FILE_NAME"

    fun with(application: Application) {
        preferences = application.getSharedPreferences(
            PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    private fun get(key: String): List<Character>? {
        val value = preferences.getString(key, null)
        val listOfMyClassObject: Type = object : TypeToken<ArrayList<Character?>?>() {}.type
        return GsonBuilder().create().fromJson(value, listOfMyClassObject) as List<Character>?
    }

    override suspend fun getCharactersFromLocal(): List<Character>? {
        return get("KEY_CHARACTER")
    }

    override suspend fun putCharactersOnLocal(characters: List<Character>, key: String) {
        val jsonString = GsonBuilder().create().toJson(characters)
        preferences.edit().putString(key, jsonString).apply()
    }
}