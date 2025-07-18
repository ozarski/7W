package com.example.the7wonders.di

import android.content.Context
import androidx.room.Room
import com.example.the7wonders.data.DatabaseExporter
import com.example.the7wonders.data.datasource.DatabaseConstants
import com.example.the7wonders.data.datasource.GameDao
import com.example.the7wonders.data.datasource.GameDatabase
import com.example.the7wonders.data.datasource.PlayerDao
import com.example.the7wonders.data.datasource.PlayerResultDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGameDatabase(@ApplicationContext context: Context): GameDatabase {
        return Room.databaseBuilder(
            context,
            GameDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideGameDao(gameDatabase: GameDatabase): GameDao {
        return gameDatabase.gameDao
    }

    @Provides
    @Singleton
    fun providePlayerDao(gameDatabase: GameDatabase): PlayerDao {
        return gameDatabase.playerDao
    }

    @Provides
    @Singleton
    fun providePlayerResultsDao(gameDatabase: GameDatabase): PlayerResultDao {
        return gameDatabase.playerResultDao
    }

    @Provides
    @Singleton
    fun provideDatabaseExporter(): DatabaseExporter {
        return DatabaseExporter(DatabaseConstants.DATABASE_NAME)
    }
}