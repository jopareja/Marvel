package com.example.marvel.domain.usecase

import com.example.marvel.data.remote.responses.comics.ServerComic
import com.example.marvel.data.repositories.ComicRepository
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(private val repository: ComicRepository) {

    suspend fun getComics(characterId: Int) : List<ServerComic> {
        return repository.getComics(characterId)
    }
}