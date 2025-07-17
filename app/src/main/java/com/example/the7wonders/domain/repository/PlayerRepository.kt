package com.example.the7wonders.domain.repository

import com.example.the7wonders.domain.model.PlayerModel
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    suspend fun getPlayers(): Flow<List<PlayerModel>>

    suspend fun addPlayer(player: PlayerModel): Long

    suspend fun deletePlayer(player: PlayerModel)

    suspend fun updatePlayer(player: PlayerModel)
}