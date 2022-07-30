package com.example.marvel.data.repositories

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.marvel.domain.data.Character
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class CharacterRespository @Inject constructor(private val dataProvider: RemoteProvider) {

    suspend fun getCharacters(offset: Int): List<Character> {
        val response = dataProvider.getCharacters(offset).map { it.toCharacter() }
        AppData.put(response, "KEY_CHARACTER")
        return response
    }

    fun getCharactersFromLocal(): List<Character>? {
        return AppData.get("KEY_CHARACTER")
    }

    object AppData {

        private lateinit var preferences: SharedPreferences
        private const val PREFERENCES_FILE_NAME = "PREFERENCES_FILE_NAME"

        fun with(application: Application) {
            preferences = application.getSharedPreferences(
                PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
        }

        fun put(characters: List<Character>, key: String) {
            val jsonString = GsonBuilder().create().toJson(characters)
            preferences.edit().putString(key, jsonString).apply()
        }

        fun get(key: String): List<Character>? {
            val value = preferences.getString(key, null)
            val listOfMyClassObject: Type = object : TypeToken<ArrayList<Character?>?>() {}.type
            return GsonBuilder().create().fromJson(value, listOfMyClassObject) as List<Character>?
        }
    }

}