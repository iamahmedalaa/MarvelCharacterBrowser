package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(offset: Int, limit: Int): List<Character>
    suspend fun getCharacterById(characterId: Int): Flow<Character>

}
