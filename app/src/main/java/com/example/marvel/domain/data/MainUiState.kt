package com.example.marvel.domain.data

import com.example.marvel.ui.main.viewmodel.HttpStatus


data class MainUiState(
    val isLoading: Boolean = false,
    val characterList: List<Character> = emptyList(),
    val requestStatus: HttpStatus = HttpStatus.GenericError
)
