package com.example.marvel.data.repositories

import com.example.marvel.domain.data.Character

interface LocalCharacterDataSource {

    suspend fun getCharactersFromLocal() : List<Character>?

    suspend fun putCharactersOnLocal(characters: List<Character>, key: String)
}