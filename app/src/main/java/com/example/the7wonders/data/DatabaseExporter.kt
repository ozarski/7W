package com.example.the7wonders.data

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class DatabaseExporter(
    private val databaseName: String
) {
    companion object {
        private const val STORAGE_PERMISSION_CODE = 100
        private const val CREATE_FILE_REQUEST_CODE = 101
    }

    private var activity: Activity? = null


    fun registerActivity(activity: Activity) {
        this.activity = activity
    }

    fun unregisterActivity() {
        this.activity = null
    }

    fun exportDatabase() {
        if (hasStoragePermission()) {
            createFilePicker()
        } else {
            requestStoragePermission()
        }
    }

    private fun hasStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            ContextCompat.checkSelfPermission(
                activity ?: throw Exception("Error: activity context is missing"),
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data = "package:${activity?.packageName}".toUri()
                activity?.startActivityForResult(intent, STORAGE_PERMISSION_CODE)
            } catch (e: Exception) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                activity?.startActivityForResult(intent, STORAGE_PERMISSION_CODE)
            }
        } else {
            ActivityCompat.requestPermissions(
                activity ?: throw Exception("Error: activity context is missing"),
                arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                STORAGE_PERMISSION_CODE
            )
        }
    }

    private fun createFilePicker() {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/x-sqlite3"
            val format = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss", Locale.ROOT)
            val dateFormatted =
                Calendar.getInstance().toInstant().atZone(ZoneId.systemDefault()).format(format)
            putExtra(Intent.EXTRA_TITLE, "${databaseName}_$dateFormatted.db")

            putExtra(
                DocumentsContract.EXTRA_INITIAL_URI,
                Uri.fromFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))
            )
        }
        activity?.startActivityForResult(intent, CREATE_FILE_REQUEST_CODE)
    }
}