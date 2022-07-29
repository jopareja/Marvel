package com.example.marvel.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    val imageUrl: String,
    val title: String
) : Parcelable
