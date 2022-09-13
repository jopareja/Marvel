package com.example.marvel.data.repositories

import com.example.marvel.data.remote.responses.comics.ServerComic

interface RemoteComicDataSource {

    suspend fun getComics(characterId: Int) : List<ServerComic>
}