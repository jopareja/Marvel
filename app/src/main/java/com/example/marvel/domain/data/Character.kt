package com.example.marvel.domain.data

data class Character(
    val imageUrl: String,
    val title: String,
    val description: String,
    val comics: List<Comic>
)
