package com.example.marvel.data.remote.responses.getComics

data class ServerComic(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: ThumbnailComic
)
