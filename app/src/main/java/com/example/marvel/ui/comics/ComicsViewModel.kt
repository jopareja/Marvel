package com.example.marvel.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.remote.responses.comics.ServerComic
import com.example.marvel.domain.data.Comic
import com.example.marvel.domain.usecase.GetComicsUseCase
import com.example.marvel.ui.main.viewmodel.HttpStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(private val useCase: GetComicsUseCase): ViewModel() {

    private val _comicList = MutableLiveData<List<Comic>>()
    val comicList: LiveData<List<Comic>> = _comicList

    private val _requestStatus = MutableLiveData<HttpStatus>()
    val requestStatus: LiveData<HttpStatus> = _requestStatus

    fun getComics(characterId: Int) {
        viewModelScope.launch {
            try {
                val response = useCase.getComics(characterId)
                _comicList.value = response
            } catch (throwable: Throwable) {
                _comicList.value = emptyList()
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