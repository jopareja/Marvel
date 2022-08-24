package com.example.marvel.ui

import android.app.Application
import com.example.marvel.data.local.LocalClient
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelAndroid: Application() {
    override fun onCreate() {
        super.onCreate()
        LocalClient.with(this)
    }
}