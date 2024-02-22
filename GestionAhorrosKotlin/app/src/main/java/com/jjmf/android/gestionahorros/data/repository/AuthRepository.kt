package com.jjmf.android.gestionahorros.data.repository

import com.jjmf.android.gestionahorros.core.Result

interface AuthRepository {
    suspend fun login(usuario: String, clave: String) : Result<String>

}