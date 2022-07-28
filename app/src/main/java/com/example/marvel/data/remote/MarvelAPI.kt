package com.example.marvel.data.remote

import com.example.marvel.R
import com.example.marvel.data.remote.responses.CharacterDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Response<CharacterDataWrapper>
}