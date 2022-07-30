package com.example.marvel.data.repositories

import com.example.marvel.data.remote.responses.characters.ServerCharacter
import com.example.marvel.domain.data.Character
import com.example.marvel.domain.data.Comic

fun ServerCharacter.toCharacter(): Character = Character(
    id = id,
    imageUrl = thumbnail.path+"."+thumbnail.extension,
    title = name,
    description = description,
    comics = comics.items.map { Comic(it.resourceURI, it.name) }
)