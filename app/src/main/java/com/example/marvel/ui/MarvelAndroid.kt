package com.example.marvel.ui

import android.app.Application
import com.example.marvel.data.repositories.CharacterRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelAndroid: Application() {
    override fun onCreate() {
        super.onCreate()
        CharacterRepository.AppData.with(this)
    }
}