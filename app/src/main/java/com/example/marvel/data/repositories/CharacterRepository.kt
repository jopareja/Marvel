package com.example.marvel.data.repositories

import com.example.marvel.domain.data.Character
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteCharacterDataSource: RemoteCharacterDataSource,
    private val localCharacterDataSource: LocalCharacterDataSource) {

    suspend fun getCharacters(offset: Int): List<Character> {
        val response = remoteCharacterDataSource.getCharacters(offset).map { it.toCharacter() }
        localCharacterDataSource.putCharactersOnLocal(response, "KEY_CHARACTER")
        return response
    }

    suspend fun getCharactersFromLocal(): List<Character>? {
        return localCharacterDataSource.getCharactersFromLocal()
    }

}