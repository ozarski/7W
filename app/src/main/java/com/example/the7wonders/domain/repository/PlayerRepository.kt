package com.example.the7wonders.domain.repository

import com.example.the7wonders.domain.model.AddPlayerToGameModel
import com.example.the7wonders.domain.model.PlayerModel
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    suspend fun getPlayersWithStats(): Flow<List<PlayerModel>>

    suspend fun getAllPlayers(): Flow<List<AddPlayerToGameModel>>

    suspend fun addPlayer(player: PlayerModel): Long

    suspend fun deletePlayer(player: PlayerModel)

    suspend fun updatePlayer(player: PlayerModel)
}