package com.example.marvel.data.remote

import com.example.marvel.data.remote.responses.ServerCharacter
import com.example.marvel.data.repositories.RemoteProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteClient @Inject constructor(private val api: MarvelAPI) : RemoteProvider {

    override suspend fun getCharacters(): List<ServerCharacter> {
        return withContext(Dispatchers.IO) {
            val data = api.getCharacters()
            data.body()?.data?.results ?: emptyList()
        }
    }
}