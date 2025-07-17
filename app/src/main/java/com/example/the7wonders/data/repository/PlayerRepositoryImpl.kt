package com.example.the7wonders.data.repository

import com.example.the7wonders.data.datasource.PlayerDao
import com.example.the7wonders.data.model.toDomainModel
import com.example.the7wonders.domain.model.AddPlayerToGameModel
import com.example.the7wonders.domain.model.PlayerModel
import com.example.the7wonders.domain.model.toPlayerEntity
import com.example.the7wonders.domain.repository.PlayerRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlayerRepositoryImpl @Inject constructor(private val playerDao: PlayerDao) :
    PlayerRepository {
    override suspend fun getPlayersWithStats(): Flow<List<PlayerModel>> {
        return playerDao.getPlayersWithStats().map {
            it.map { dto -> dto.toDomainModel() }
        }
    }

    override suspend fun getAllPlayers(): Flow<List<AddPlayerToGameModel>> {
        return playerDao.getAllPlayers().map {
            it.map { dto -> dto.toDomainModel() }.sortedBy { player -> player.name }
        }
    }

    override suspend fun addPlayer(player: PlayerModel): Long {
        return playerDao.addPlayer(player.toPlayerEntity())
    }

    override suspend fun deletePlayer(player: PlayerModel) {
        //TODO("Add actual error handling")
        if (player.id == null) return
        playerDao.deletePlayer(player.id)
    }

    override suspend fun updatePlayer(player: PlayerModel) {
        playerDao.updatePlayer(player.toPlayerEntity())
    }
}