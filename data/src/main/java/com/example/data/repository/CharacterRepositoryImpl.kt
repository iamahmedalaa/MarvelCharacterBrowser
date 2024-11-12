package com.example.data.repository

import com.example.data.remote.MarvelApiService
import com.example.data.remote.MarvelAuthUtil
import com.example.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: MarvelApiService  // Retrofit API service
) : CharacterRepository {

    override suspend fun getCharacterById(characterId: Int): Flow<Character> = flow {
        val response = apiService.getCharacterById(characterId)  // Retrofit call
        if (response.isSuccessful) {
            response.body()?.data?.results?.firstOrNull()?.let {
                emit(it.toCharacter())  // Map API response to domain model
            }
        }
    }
    override suspend fun getCharacters(offset: Int, limit: Int): List<Character> {
        val timestamp = System.currentTimeMillis().toString()
        val (publicKey, hash) = MarvelAuthUtil.generateAuthParams(timestamp)
        val response = apiService.getCharacters(publicKey, timestamp, hash, limit, offset)
        return response.results.map { it.toDomainCharacter() }
    }
}
