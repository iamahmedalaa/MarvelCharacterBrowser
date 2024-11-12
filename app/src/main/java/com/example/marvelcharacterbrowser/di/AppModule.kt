package com.example.marvelcharacterbrowser.di

import com.example.data.remote.MarvelApiService
import com.example.data.repository.CharacterRepositoryImpl
import com.example.domain.repository.CharacterRepository
import com.example.domain.usecase.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(apiService: MarvelApiService): CharacterRepository {
        return CharacterRepositoryImpl(apiService)
    }

    @Provides
    fun provideGetCharactersUseCase(repository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(repository)
    }
}
