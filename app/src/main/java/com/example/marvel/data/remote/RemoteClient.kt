package com.example.marvel.data.remote

import com.example.marvel.data.remote.responses.ServerCharacter
import com.example.marvel.data.repositories.RemoteProvider

class RemoteClient : RemoteProvider {

    override suspend fun getCharacters(): List<ServerCharacter> {
        TODO("Not yet implemented")
    }
}