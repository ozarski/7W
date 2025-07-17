package com.example.the7wonders.di

import com.example.the7wonders.data.repository.GameRepositoryImpl
import com.example.the7wonders.domain.repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideGamesRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository

    @Binds
    @Singleton
    abstract fun providePlayerRepository(playerRepositoryImpl: GameRepositoryImpl): GameRepository

    @Binds
    @Singleton
    abstract fun providePlayerResultRepository(playerResultRepositoryImpl: GameRepositoryImpl): GameRepository
}