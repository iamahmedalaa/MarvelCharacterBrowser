package com.example.marvelcharacterbrowser.userInterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.CharacterRepository
import com.example.domain.model.CharacterMarvel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _characterMarvel = MutableStateFlow<CharacterMarvel?>(null)
    val characters: StateFlow<CharacterMarvel?> get() = _characterMarvel

    fun loadCharacterById(characterId: Int) {
        viewModelScope.launch {
            characterRepository.getCharacterById(characterId).collect { character ->
                characters.also { _characterMarvel.value = it }
            }
        }
    }
}
