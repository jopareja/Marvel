package com.example.marvel.data.repositories

import com.example.marvel.data.remote.responses.characters.ServerCharacter
import com.example.marvel.data.remote.responses.comics.ServerComic

interface RemoteProvider {

    suspend fun getCharacters(offset: Int) : List<ServerCharacter>

    suspend fun getComics(characterId: Int) : List<ServerComic>
}