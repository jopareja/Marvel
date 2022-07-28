package com.example.marvel.data.remote

import com.example.marvel.R
import com.example.marvel.data.remote.responses.ServerCharacter
import com.example.marvel.data.repositories.RemoteProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class RemoteClient @Inject constructor(private val api: MarvelAPI) : RemoteProvider {

    override suspend fun getCharacters(): List<ServerCharacter> {
        return withContext(Dispatchers.IO) {
            val data = api.getCharacters("1",
                R.string.public_key.toString(),
                toMD5("1", R.string.public_key.toString(), R.string.private_key.toString()))
            data.body()?.data?.results ?: emptyList()
        }
    }


    private fun toMD5(ts: String, publicKey: String, privateKey: String) : String {
        val input = ts+publicKey+privateKey
        val md = MessageDigest.getInstance("MD5")
        val byte = md.digest(input.toByteArray())
        return BigInteger(1, byte).toString(16).padStart(32, '0')
    }
}