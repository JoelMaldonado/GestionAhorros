package com.jjmf.android.gestionahorros.domain.repository

import androidx.lifecycle.ViewModel
import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.data.dto.CategoriaDto
import com.jjmf.android.gestionahorros.data.repository.CategoriaRepository
import com.jjmf.android.gestionahorros.data.service.CategoriaService
import com.jjmf.android.gestionahorros.domain.model.Categoria
import javax.inject.Inject

class CategoriaRepositoryImpl @Inject constructor(
    private val api: CategoriaService
) : CategoriaRepository {
    override suspend fun getAll(): Result<List<Categoria>> {
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

    override suspend fun insert(categoria: Categoria): Result<String> {
        return try {
            val call = api.insert(categoria.toDto())
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