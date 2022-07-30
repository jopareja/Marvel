package com.example.marvel.data.repositories

import com.example.marvel.domain.data.Comic
import javax.inject.Inject

class ComicRepository @Inject constructor(private val dataProvider: RemoteProvider) {

    suspend fun getComics(characterId: Int): List<Comic> {
        return dataProvider.getComics(characterId).map { it.toComic() }
    }
}