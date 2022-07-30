package com.example.marvel.domain.usecase

import com.example.marvel.data.repositories.ComicRepository
import com.example.marvel.domain.data.Comic
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(private val repository: ComicRepository) {

    suspend fun getComics(characterId: Int) : List<Comic> {
        return repository.getComics(characterId)
    }
}