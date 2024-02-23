package com.jjmf.android.gestionahorros.domain.model

import com.jjmf.android.gestionahorros.data.dto.CategoriaDto
import com.jjmf.android.gestionahorros.data.dto.CuentaDto

data class Cuenta(
    val id:Int?,
    val nombre:String?,
    val color: ColorCategoria,
    val icono: Icono,
    val activo:Boolean
) {
    fun toDto(): CuentaDto {
        return CuentaDto(
            id = id,
            nombre = nombre,
            color = color.id,
            icono = icono.id,
            activo = activo
        )
    }
}

