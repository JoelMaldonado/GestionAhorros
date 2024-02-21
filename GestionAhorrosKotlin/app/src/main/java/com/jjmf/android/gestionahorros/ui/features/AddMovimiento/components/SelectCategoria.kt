package com.jjmf.android.gestionahorros.ui.features.AddMovimiento.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SelectCategoria() {

    val select = remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Categoría", color = Color.Gray, fontSize = 14.sp)
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(8) {
                Column(
                    modifier = Modifier.size(80.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (select.intValue == it) Color.Blue else Color.Blue.copy(0.3f))
                        .clickable {
                            select.intValue = it
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Outlined.DirectionsBus,
                        contentDescription = null,
                        tint = Color.White
                    )

                    Text(
                        text = "Casa",
                        color = Color.White
                    )
                }
            }
        }
    }
}