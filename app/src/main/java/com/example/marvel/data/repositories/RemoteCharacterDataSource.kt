package com.example.marvel.data.repositories

import com.example.marvel.data.remote.responses.characters.ServerCharacter

interface RemoteCharacterDataSource {

    suspend fun getCharacters(offset: Int) : List<ServerCharacter>
}