package com.jjmf.android.gestionahorros.core

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class UriToFile(private val context: Context) {

    @Throws(IOException::class)
    fun getFileFromUri(uri: Uri): File {
        val contentResolver: ContentResolver = context.contentResolver

        // Verifica si la URI es de tipo File URI
        if (uri.scheme == "file") {
            return File(uri.path!!)
        }

        // Si no es una File URI, intenta obtener el archivo de la URI
        val inputStream = contentResolver.openInputStream(uri)
        val tempFile = createTempFile()
        inputStream.use { input ->
            FileOutputStream(tempFile).use { output ->
                input?.copyTo(output)
            }
        }
        return tempFile
    }

    private fun createTempFile(): File {
        val timeStamp = System.currentTimeMillis()
        val fileName = "temp_$timeStamp"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDir)
    }

}