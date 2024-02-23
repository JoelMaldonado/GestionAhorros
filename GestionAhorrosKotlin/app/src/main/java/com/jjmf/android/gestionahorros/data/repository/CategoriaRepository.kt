package com.jjmf.android.gestionahorros.data.repository

import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.domain.model.Categoria

interface CategoriaRepository {

    suspend fun getAll():Result<List<Categoria>>
    suspend fun insert(categoria: Categoria): Result<String>

}