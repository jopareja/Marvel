package com.example.marvel.data.repositories

import com.example.marvel.data.remote.responses.getComics.ServerComic
import javax.inject.Inject

class ComicRepository @Inject constructor(private val dataProvider: RemoteProvider) {

    suspend fun getComics(characterId: Int) : List<ServerComic> {
        return dataProvider.getComics(characterId)
    }
}