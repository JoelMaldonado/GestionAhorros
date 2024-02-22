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
                if (call.body()?.success == true){
                    Result.success(call.body()?.data?.token.toString())
                } else {
                    Log.d("tagito", "Entro")
                    Result.failure(Exception(call.body()?.message.toString()))
                }
            } else {
                Result.failure(Exception(call.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}