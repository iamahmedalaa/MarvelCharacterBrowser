package com.example.marvelcharacterbrowser.userInterface.characterdetail

import com.example.domain.repository.CharacterRepository


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val characterId: Int = savedStateHandle["characterId"] ?: 0

    private val _character = MutableStateFlow<Character?>(null)
    val character = _character.asStateFlow()

    init {
        loadCharacterDetails()
    }

    private fun loadCharacterDetails() {
        viewModelScope.launch {
            val result = repository.getCharacterById(characterId)
            _character.value = result
        }
    }
}
