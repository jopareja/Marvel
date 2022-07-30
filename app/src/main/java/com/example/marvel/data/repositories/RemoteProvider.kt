package com.example.marvel.data.repositories

import com.example.marvel.data.remote.responses.ServerCharacter
import com.example.marvel.data.remote.responses.getComics.ServerComic

interface RemoteProvider {

    suspend fun getCharacters(offset: Int) : List<ServerCharacter>

    suspend fun getComics(characterId: Int) : List<ServerComic>
}