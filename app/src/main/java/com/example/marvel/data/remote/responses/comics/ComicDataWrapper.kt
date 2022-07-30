package com.example.marvel.data.remote.responses.comics

data class ComicDataWrapper(
    val code: Int,
    val status: String,
    val data: ComicDataContainer
)
