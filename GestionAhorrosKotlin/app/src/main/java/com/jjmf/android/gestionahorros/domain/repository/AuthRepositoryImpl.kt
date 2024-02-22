package com.jjmf.android.gestionahorros.domain.repository

import com.jjmf.android.gestionahorros.data.repository.AuthRepository
import com.jjmf.android.gestionahorros.data.service.ApiService
import com.jjmf.android.gestionahorros.data.service.request.LoginRequest
import javax.inject.Inject
import com.jjmf.android.gestionahorros.core.Result

class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService
) : AuthRepository {
    override suspend fun login(usuario: String, clave: String): Result<String> {
        return try {
            val body = LoginRequest(correo = usuario, password = clave)
            val call = api.login(body)
            if (call.isSuccessful) {
                if (call.body()?.isSuccess == true){
                    Result.Correcto(call.body()?.data?.token.toString())
                } else {
                    Result.Error(call.body()?.message.toString())
                }
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

}