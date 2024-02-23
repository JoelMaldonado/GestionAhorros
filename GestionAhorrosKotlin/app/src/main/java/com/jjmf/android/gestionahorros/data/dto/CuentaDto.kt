package com.jjmf.android.gestionahorros.data.dto

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import com.jjmf.android.gestionahorros.domain.model.ColorCategoria
import com.jjmf.android.gestionahorros.domain.model.Cuenta
import com.jjmf.android.gestionahorros.domain.model.Icono
import com.jjmf.android.gestionahorros.ui.features.AddCategoria.components.getColors
import com.jjmf.android.gestionahorros.ui.features.AddCategoria.components.getIconos

data class CuentaDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("color") val color: Int?,
    @SerializedName("icono") val icono: Int?,
    @SerializedName("activo") val activo: Boolean?
) {
    fun toDomain(): Cuenta {
        val ic = getIconos().find { it.id == icono } ?: Icono(0, Icons.Default.QuestionMark)
        return Cuenta(id = id,
            nombre = nombre,
            color = getColors().find { it.id == color } ?: ColorCategoria(0, color = Color.Gray),
            icono = ic,
            activo = activo ?: false)
    }
}
