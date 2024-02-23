package com.jjmf.android.gestionahorros.data.repository

import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.domain.model.Usuario

interface AuthRepository {
    suspend fun login(usuario: String, clave: String): Result<String>

    suspend fun token(): Result<Usuario>

}