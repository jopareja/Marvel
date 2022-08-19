package com.example.marvel.data.repositories

import com.example.marvel.domain.data.Character
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val dataProvider: RemoteProvider,
    private val localProvider: LocalProvider) {

    suspend fun getCharacters(offset: Int): List<Character> {
        val response = dataProvider.getCharacters(offset).map { it.toCharacter() }
        localProvider.putCharactersOnLocal(response, "KEY_CHARACTER")
        return response
    }

    suspend fun getCharactersFromLocal(): List<Character>? {
        return localProvider.getCharactersFromLocal()
    }

}