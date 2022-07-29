package com.example.marvel.data.remote.responses.getComics

data class ComicDataWrapper(
    val code: Int,
    val status: String,
    val data: ComicDataContainer
)
