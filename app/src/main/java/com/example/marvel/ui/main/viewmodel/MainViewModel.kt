package com.example.marvel.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.domain.data.Character
import com.example.marvel.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetCharacterUseCase
) : ViewModel() {

    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>> = _characterList

    private val _requestStatus = MutableLiveData<HttpStatus>()
    val requestStatus: LiveData<HttpStatus> = _requestStatus

    fun getCharacterList(offset: Int) {
        viewModelScope.launch {
            try {
                _characterList.value = useCase.getCharacters(offset)
            } catch (throwable: Throwable) {
                _characterList.value = emptyList()
                when (throwable) {
                    is IOException -> _requestStatus.value = HttpStatus.IOException
                    is HttpException -> when (throwable.code()) {
                        in 400..499 -> _requestStatus.value = HttpStatus.HTTP400
                        in 500..599 -> _requestStatus.value = HttpStatus.HTTP500
                        else -> _requestStatus.value = HttpStatus.GenericError
                    }
                }
            }
        }
    }
}