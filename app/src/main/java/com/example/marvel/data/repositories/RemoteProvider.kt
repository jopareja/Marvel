package com.example.marvel.data.repositories

import com.example.marvel.data.remote.responses.ServerCharacter

interface RemoteProvider {

    suspend fun getCharacters() : List<ServerCharacter>
}