package com.example.marvel.data.remote.responses.comics

data class ServerComic(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: ThumbnailComic
)
