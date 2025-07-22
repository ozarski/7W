package com.example.the7wonders.domain.repository

import com.example.the7wonders.data.model.GameDetailsModel
import com.example.the7wonders.domain.model.GameModel
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    suspend fun getGames(): Flow<List<GameModel>>

    suspend fun getGameDetails(id: Long): GameDetailsModel

    suspend fun addGame(game: GameModel): Long

    suspend fun deleteGame(game: GameModel)
}