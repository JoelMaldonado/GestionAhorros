package com.jjmf.android.gestionahorros.domain.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.jjmf.android.gestionahorros.core.UriToFile
import com.jjmf.android.gestionahorros.data.repository.ImageRepository
import com.jjmf.android.gestionahorros.data.service.ImageService
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import javax.inject.Inject
import com.jjmf.android.gestionahorros.core.Result

class ImageRepositoryImpl @Inject constructor(
    private val api: ImageService,
    @ApplicationContext private val context: Context
) : ImageRepository {
    override suspend fun insert(uri: Uri): Result<String> {
        return try {
            val file = UriToFile(context).getFileFromUri(uri)
            val call = api.insert(
                MultipartBody.Part.createFormData(
                    "imagen",
                    file.name,
                    file.asRequestBody()
                )
            )
            if (call.isSuccessful) {
                val body = call.body()
                if (body?.isSuccess == true) Result.Correcto(body.data)
                else Result.Error(body?.message)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }


}