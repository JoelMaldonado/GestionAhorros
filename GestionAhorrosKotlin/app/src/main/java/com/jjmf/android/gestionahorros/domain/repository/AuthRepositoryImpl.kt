package com.jjmf.android.gestionahorros.domain.repository

import android.util.Log
import com.jjmf.android.gestionahorros.data.repository.AuthRepository
import com.jjmf.android.gestionahorros.data.service.ApiService
import com.jjmf.android.gestionahorros.data.service.request.LoginRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService
) : AuthRepository {
    override suspend fun login(usuario: String, clave: String): Result<String> {
        return try {
            val body = LoginRequest(correo = usuario, password = clave)
            val call = api.login(body)
            if (call.isSuccessful) {
                Result.success(call.body()?.token.toString())
            } else {
                Result.failure(Exception(call.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}