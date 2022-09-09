package com.example.marvel.domain.data



data class MainUiState(
    val isLoading: Boolean = false,
    val characterList: List<Character> = emptyList(),
    val isError: Boolean = false,
    val userMessage: Message = Message.Empty
)
