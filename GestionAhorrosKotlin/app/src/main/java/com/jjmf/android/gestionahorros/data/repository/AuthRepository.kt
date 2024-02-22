package com.jjmf.android.gestionahorros.data.repository

interface AuthRepository {
    suspend fun login(usuario: String, clave: String) : Result<String>

}