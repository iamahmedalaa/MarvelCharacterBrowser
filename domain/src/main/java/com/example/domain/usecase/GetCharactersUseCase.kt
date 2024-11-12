package com.example.domain.usecase

import com.example.domain.repository.CharacterRepository

class GetCharactersUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(offset: Int, limit: Int) = repository.getCharacters(offset, limit)
}
