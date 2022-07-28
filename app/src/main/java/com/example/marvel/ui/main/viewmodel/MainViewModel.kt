package com.example.marvel.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.domain.data.Character
import com.example.marvel.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharacter: GetCharacterUseCase
) : ViewModel() {

    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>> = _characterList

    fun getCharacterList() {
        viewModelScope.launch {
            try {
                _characterList.value = getCharacter.invoke()
            } catch (throwable: Throwable) {
                _characterList.value = emptyList()
            }
        }
    }
}