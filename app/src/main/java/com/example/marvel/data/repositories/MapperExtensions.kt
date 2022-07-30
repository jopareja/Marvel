package com.example.marvel.data.repositories

import com.example.marvel.data.remote.responses.characters.ServerCharacter
import com.example.marvel.data.remote.responses.comics.ServerComic
import com.example.marvel.domain.data.Character
import com.example.marvel.domain.data.Comic

fun ServerCharacter.toCharacter(): Character = Character(
    id = id,
    imageUrl = thumbnail.path+"."+thumbnail.extension,
    title = name,
    description = description
)

fun ServerComic.toComic(): Comic = Comic(
    imageUrl = thumbnail.path+"."+thumbnail.extension,
    title = title
)