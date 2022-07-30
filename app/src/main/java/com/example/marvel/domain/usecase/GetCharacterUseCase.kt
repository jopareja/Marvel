package com.example.marvel.domain.usecase

import com.example.marvel.data.repositories.CharacterRespository
import com.example.marvel.domain.data.Character
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val repository: CharacterRespository) {

    suspend fun getCharacters(offset: Int) : List<Character> {
        return repository.getCharacters(offset)
    }

}