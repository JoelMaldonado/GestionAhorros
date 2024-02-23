package com.jjmf.android.gestionahorros.ui.features.AddMovimiento.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.gestionahorros.domain.model.Categoria


@Composable
fun SelectCategoria(
    list: List<Categoria>
) {

    val select = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Categoría", color = Color.Gray, fontSize = 14.sp)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(list) {
                Column(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (select.intValue == it.id) it.color.color else it.color.color.copy(
                                0.3f
                            )
                        )
                        .clickable {
                            select.intValue = it.id ?: 0
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = it.icono.icon,
                        contentDescription = null,
                        tint = Color.White
                    )

                    Text(
                        text = it.nombre.toString().capitalize(Locale.current),
                        color = Color.White
                    )
                }
            }
        }
    }
}