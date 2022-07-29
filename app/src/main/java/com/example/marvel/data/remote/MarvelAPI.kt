package com.example.marvel.data.remote

import com.example.marvel.data.remote.responses.CharacterDataWrapper
import com.example.marvel.data.remote.responses.getComics.ComicDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Response<CharacterDataWrapper>

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getComics(
        @Path(value = "characterId", encoded = true) characterId: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Response<ComicDataWrapper>
}