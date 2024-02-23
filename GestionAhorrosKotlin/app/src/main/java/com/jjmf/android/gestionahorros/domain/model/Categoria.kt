package com.jjmf.android.gestionahorros.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.jjmf.android.gestionahorros.data.dto.CategoriaDto
import com.jjmf.android.gestionahorros.ui.features.AddMovimiento.TipoMovimiento

data class Categoria(
    val id: Int?,
    val nombre: String?,
    val tipo: TipoMovimiento?,
    val color: ColorCategoria,
    val icono: Icono,
    val activo: Boolean
){
    fun toDto(): CategoriaDto{
        return CategoriaDto(
            id = id,
            nombre = nombre,
            tipo = tipo?.name,
            color = color.id,
            icono = icono.id,
            activo = activo
        )
    }
}

data class Icono(
    val id: Int,
    val icon: ImageVector
)

data class ColorCategoria(
    val id: Int,
    val color: Color
)