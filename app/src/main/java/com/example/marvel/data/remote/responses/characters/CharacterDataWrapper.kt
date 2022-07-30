package com.example.marvel.data.remote.responses.characters

data class CharacterDataWrapper(
    val code: Int,
    val status: String,
    val data: CharacterDataContainer
)
