package com.example.marvel.data.remote.responses.characters


data class ServerCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ImageThumbnail,
    val comics: ServerComicList
)
