package com.jjmf.android.gestionahorros.data.dto

import com.jjmf.android.gestionahorros.domain.model.Cuenta

data class CuentaDto(
    val id:Int?,
    val nombre:String?,
    val activo:Boolean?
) {
     fun toDomain(): Cuenta{
         return Cuenta(
             id = id,
             nombre = nombre,
             activo = activo ?: false
         )
     }
}
