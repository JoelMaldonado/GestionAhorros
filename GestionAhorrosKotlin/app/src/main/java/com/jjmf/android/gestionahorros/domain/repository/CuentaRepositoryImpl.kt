package com.jjmf.android.gestionahorros.domain.repository

import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.data.repository.CuentaRepository
import com.jjmf.android.gestionahorros.data.service.CuentaService
import com.jjmf.android.gestionahorros.domain.model.Cuenta
import javax.inject.Inject

class CuentaRepositoryImpl @Inject constructor(
    private val api: CuentaService
) : CuentaRepository {
    override suspend fun getList(): Result<List<Cuenta>> {
        return try {
            val call = api.getList()
            if (call.isSuccessful) {
                val body = call.body()
                if (body?.isSuccess == true) Result.Correcto(body.data.map { it.toDomain() })
                else Result.Error(body?.message)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun insert(data: Cuenta): Result<String> {
        return try {
            val call = api.insert(data.toDto())
            if (call.isSuccessful){
                val body = call.body()
                if (body?.isSuccess == true){
                    Result.Correcto("Se inserto")
                }else{
                    Result.Error(body?.message)
                }
            } else {
                Result.Error(call.message())
            }
        }catch (e:Exception){
            Result.Error(e.message)
        }
    }
}