package com.example.marvel.di

import com.example.marvel.data.local.LocalClient
import com.example.marvel.data.remote.MarvelAPI
import com.example.marvel.data.remote.RemoteClient
import com.example.marvel.data.repositories.LocalProvider
import com.example.marvel.data.repositories.RemoteProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataRemoteModule {

    @Singleton
    @Provides
    fun provideAPI(retrofit: Retrofit) : MarvelAPI {
        return retrofit.create(MarvelAPI::class.java)
    }

    private const val BASE_URL = "https://gateway.marvel.com/"

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideRemoteInterface(api: MarvelAPI) : RemoteProvider {
        return RemoteClient(api)
    }

    @Singleton
    @Provides
    fun provideLocalInterface() : LocalProvider {
        return LocalClient
    }

}