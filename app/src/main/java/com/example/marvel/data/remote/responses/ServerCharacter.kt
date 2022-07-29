package com.example.marvel.data.remote.responses


data class ServerCharacter(
    val name: String,
    val description: String,
    val thumbnail: ImageThumbnail,
    val comics: ServerComic
)
