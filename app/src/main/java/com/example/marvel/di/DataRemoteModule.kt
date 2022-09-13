package com.example.marvel.di

import com.example.marvel.data.local.LocalClient
import com.example.marvel.data.remote.MarvelAPI
import com.example.marvel.data.remote.RemoteClient
import com.example.marvel.data.repositories.LocalCharacterDataSource
import com.example.marvel.data.repositories.RemoteCharacterDataSource
import com.example.marvel.data.repositories.RemoteComicDataSource
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

    //Provides API to RemoteClient
    @Singleton
    @Provides
    fun provideAPI(retrofit: Retrofit) : MarvelAPI {
        return retrofit.create(MarvelAPI::class.java)
    }

    private const val BASE_URL = "https://gateway.marvel.com/"

    //Provides Retrofit to API
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    //Provides to ComicRepository
    @Singleton
    @Provides
    fun provideRemoteComicInterface(api: MarvelAPI) : RemoteComicDataSource {
        return RemoteClient(api)
    }

    //Provides to CharacterRepository
    @Singleton
    @Provides
    fun provideRemoteCharacterInterface(api: MarvelAPI) : RemoteCharacterDataSource {
        return RemoteClient(api)
    }

    @Singleton
    @Provides
    fun provideLocalInterface() : LocalCharacterDataSource {
        return LocalClient
    }

}