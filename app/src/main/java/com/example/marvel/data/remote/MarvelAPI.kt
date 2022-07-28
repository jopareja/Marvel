package com.example.marvel.data.remote

import com.example.marvel.data.remote.responses.CharacterDataWrapper
import retrofit2.Response
import retrofit2.http.GET

interface MarvelAPI {

    @GET("/v1/public/characters")
    suspend fun getCharacters(): Response<CharacterDataWrapper>
}