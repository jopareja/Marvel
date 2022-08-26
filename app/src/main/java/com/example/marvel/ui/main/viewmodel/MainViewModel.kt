package com.example.marvel.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.domain.data.MainUiState
import com.example.marvel.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetCharacterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun getCharacterList(offset: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                _uiState.update {
                    it.copy(isLoading = false, characterList = useCase.getCharacters(offset)) }
            } catch (throwable: Throwable) {
                _uiState.update {
                    it.copy(isLoading = false, characterList = useCase.getSavedCharacters() ?: emptyList()) }
                when (throwable) {
                    is IOException -> _uiState.update { it.copy(requestStatus = HttpStatus.IOException) }
                    is HttpException -> when (throwable.code()) {
                        in 400..499 -> _uiState.update { it.copy(requestStatus = HttpStatus.HTTP400) }
                        in 500..599 -> _uiState.update { it.copy(requestStatus = HttpStatus.HTTP500) }
                        else -> _uiState.update { it.copy(requestStatus = HttpStatus.GenericError) }
                    }
                }
            }
        }
    }
}