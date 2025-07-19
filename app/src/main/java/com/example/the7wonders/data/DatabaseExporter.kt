package com.example.the7wonders.data

import android.content.Context
import android.net.Uri
import com.example.the7wonders.data.datasource.DatabaseConstants
import com.example.the7wonders.data.datasource.GameDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import javax.inject.Inject

class DatabaseExporter @Inject constructor(
    private val context: Context,
    private val gameDatabase: GameDatabase
) {

    suspend fun exportDatabaseToUri(
        uri: Uri,
        databaseName: String = DatabaseConstants.DATABASE_NAME
    ): Result<String> = withContext(Dispatchers.IO) {
        try {
            //consolidate database files before exporting
            val cursor = gameDatabase.query("PRAGMA wal_checkpoint", arrayOf())
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
}