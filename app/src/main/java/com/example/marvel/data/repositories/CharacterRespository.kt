package com.example.marvel.data.repositories

import com.example.marvel.domain.data.Character
import javax.inject.Inject

class CharacterRespository @Inject constructor(private val dataProvider: RemoteProvider) {

    suspend fun getCharacters(offset: Int): List<Character> {
        return dataProvider.getCharacters(offset).map { it.toCharacter() }
    }
}