package com.jjmf.android.gestionahorros.domain.repository

import com.jjmf.android.gestionahorros.data.repository.AuthRepository
import com.jjmf.android.gestionahorros.data.service.ApiService
import com.jjmf.android.gestionahorros.data.service.request.LoginRequest
import javax.inject.Inject
import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.domain.model.Usuario

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

    override suspend fun token() : Result<Usuario>{
        return try {
            val call = api.token()
            if (call.isSuccessful){
                val body = call.body()
                if (body?.isSuccess == true){
                    Result.Correcto(body.data.toDomain())
                } else {
                    Result.Error(body?.message.toString())
                }
            }else{
                Result.Error(call.message())
            }
        }catch (e:Exception){
            Result.Error(e.message)
        }
    }

}