package com.example.the7wonders.data

import android.content.Context
import android.net.Uri
import androidx.room.Room
import com.example.the7wonders.data.datasource.DatabaseConstants
import com.example.the7wonders.data.datasource.GameDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

class DatabaseManager @Inject constructor(
    private val context: Context,
    private var database: GameDatabase
) {
    private val lock = Any()

    fun getDatabase(): GameDatabase {
        synchronized(lock) {
            return database
        }
    }

    fun reloadDatabase() {
        synchronized(lock) {
            // Close the existing database
            database.close()

            // Create a new instance
            database = Room.databaseBuilder(
                context,
                GameDatabase::class.java,
                DatabaseConstants.DATABASE_NAME
            ).build()
        }
    }

    suspend fun exportDatabaseToUri(
        uri: Uri,
        databaseName: String = DatabaseConstants.DATABASE_NAME
    ): Result<String> = withContext(Dispatchers.IO) {
        try {
            //consolidate database files before exporting
            val cursor = database.query("PRAGMA wal_checkpoint", arrayOf())
            cursor.moveToFirst()
            val currentDbFile = context.getDatabasePath(databaseName)

            if (!currentDbFile.exists()) {
                return@withContext Result.failure(Exception("Database file not found"))
            }

            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                FileInputStream(currentDbFile).use { inputStream ->
                    inputStream.copyTo(outputStream)
                }
            } ?: return@withContext Result.failure(Exception("Could not open output stream"))

            Result.success("Database exported successfully")

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun importDatabaseFromUri(
        uri: Uri,
        databaseName: String = DatabaseConstants.DATABASE_NAME
    ): Result<String> = withContext(Dispatchers.IO) {
        try {
            //consolidate database files before importing
            val cursor = database.query("PRAGMA wal_checkpoint", arrayOf())
            cursor.moveToFirst()
            cursor.close()
            database.close()

            val currentDbFile = context.getDatabasePath(databaseName)
            //delete temporary room files
            val shmFile = File("${currentDbFile.path}-shm")
            val walFile = File("${currentDbFile.path}-wal")
            shmFile.delete()
            walFile.delete()

            val backupFile = File(currentDbFile.parent, "${databaseName}.backup")
            if (currentDbFile.exists()) {
                currentDbFile.copyTo(backupFile, overwrite = true)
            }

            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                FileOutputStream(currentDbFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            } ?: return@withContext Result.failure(Exception("Could not open input stream"))

            if (!isDatabaseValid(currentDbFile)) {
                if (backupFile.exists()) {
                    backupFile.copyTo(currentDbFile, overwrite = true)
                    backupFile.delete()
                }
                return@withContext Result.failure(Exception("Invalid database file"))
            }

            backupFile.delete()
            reloadDatabase()

            Result.success("Database imported successfully")

        } catch (e: Exception) {
            println(e.message)
            Result.failure(e)
        }
    }

    private fun isDatabaseValid(databaseFile: File): Boolean {
        return try {
            val bytes = ByteArray(16)
            FileInputStream(databaseFile).use {
                it.read(bytes)
            }
            String(bytes).startsWith("SQLite format 3")
        } catch (e: Exception) {
            false
        }
    }

}