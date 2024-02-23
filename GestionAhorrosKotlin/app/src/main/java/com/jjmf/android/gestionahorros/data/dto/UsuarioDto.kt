package com.jjmf.android.gestionahorros.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.android.gestionahorros.domain.model.Usuario

data class UsuarioDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("correo") val correo: String?
) {
    fun toDomain(): Usuario {
        return Usuario(
            id = id, nombre = nombre, correo = correo
        )
    }
}
