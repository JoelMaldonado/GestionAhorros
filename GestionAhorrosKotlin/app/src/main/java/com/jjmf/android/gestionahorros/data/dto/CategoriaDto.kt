package com.jjmf.android.gestionahorros.data.dto

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.gson.annotations.SerializedName
import com.jjmf.android.gestionahorros.domain.model.Categoria
import com.jjmf.android.gestionahorros.domain.model.ColorCategoria
import com.jjmf.android.gestionahorros.domain.model.Icono
import com.jjmf.android.gestionahorros.ui.features.AddCategoria.components.getColors
import com.jjmf.android.gestionahorros.ui.features.AddCategoria.components.getIconos
import com.jjmf.android.gestionahorros.ui.features.AddMovimiento.TipoMovimiento
import com.jjmf.android.gestionahorros.ui.theme.ColorP1

data class CategoriaDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("tipo") val tipo: String?,
    @SerializedName("color") val color: Int?,
    @SerializedName("icono") val icono: Int?,
    @SerializedName("activo") val activo: Boolean?,
) {
    fun toDomain(): Categoria {
        return Categoria(
            id = id,
            nombre = nombre,
            tipo = TipoMovimiento.valueOf(tipo.toString()),
            color = getColors().find { it.id == color } ?: ColorCategoria(0, color = Color.Gray),
            icono = getIconos().find { it.id == icono } ?: Icono(
                0,
                icon = Icons.Default.QuestionMark
            ),
            activo = activo ?: false
        )
    }
}