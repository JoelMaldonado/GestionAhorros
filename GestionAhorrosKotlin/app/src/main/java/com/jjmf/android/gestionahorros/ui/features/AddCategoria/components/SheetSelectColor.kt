package com.jjmf.android.gestionahorros.ui.features.AddCategoria.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.gestionahorros.domain.model.ColorCategoria
import com.jjmf.android.gestionahorros.ui.theme.ColorTitulo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetSelectColor(
    close: () -> Unit,
    click: (ColorCategoria) -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = {
            close()
        },
        containerColor = Color.White
    ) {

        Text(
            text = "Selecciona un icono",
            color = ColorTitulo,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                items(getColors()) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(it.color)
                            .clickable {
                                click(it)
                                close()
                            }
                    )
                }
            }
        )
    }


}

fun getColors(): List<ColorCategoria> {
    return listOf(
        ColorCategoria(id = 1, Color(0xFF58f0dc)),
        ColorCategoria(id = 2, Color(0xFF40e0d0)),
        ColorCategoria(id = 3, Color(0xFF0cc0b1)),
        ColorCategoria(id = 4, Color(0xFF079a91)),

        ColorCategoria(id = 5, Color(0xFF87ced9)),
        ColorCategoria(id = 6, Color(0xFF4fb1c1)),
        ColorCategoria(id = 7, Color(0xFF3495a6)),
        ColorCategoria(id = 8, Color(0xFF2e798c)),

        ColorCategoria(id = 9, Color(0xFF86cfa1)),
        ColorCategoria(id = 10, Color(0xFF54b37a)),
        ColorCategoria(id = 11, Color(0xFF2e8b57)),
        ColorCategoria(id = 12, Color(0xFF22794a)),

        ColorCategoria(id = 13, Color(0xFFfffc86)),
        ColorCategoria(id = 14, Color(0xFFfff141)),
        ColorCategoria(id = 15, Color(0xFFffe20d)),
        ColorCategoria(id = 16, Color(0xFFffd300)),

        ColorCategoria(id = 17, Color(0xFFffda46)),
        ColorCategoria(id = 18, Color(0xFFffc71b)),
        ColorCategoria(id = 19, Color(0xFFffa500)),
        ColorCategoria(id = 20, Color(0xFFe27c00)),

        ColorCategoria(id = 21, Color(0xFFffa071)),
        ColorCategoria(id = 22, Color(0xFFff7f50)),
        ColorCategoria(id = 23, Color(0xFFfe4711)),
        ColorCategoria(id = 24, Color(0xFFef2d07)),

        ColorCategoria(id = 25, Color(0xFFff5757)),
        ColorCategoria(id = 26, Color(0xFFff5757)),
        ColorCategoria(id = 27, Color(0xFFff2323)),
        ColorCategoria(id = 28, Color(0xFFFF0000)),

        ColorCategoria(id = 29, Color(0xFFffa2b3)),
        ColorCategoria(id = 30, Color(0xFFfe6e8b)),
        ColorCategoria(id = 31, Color(0xFFf83b66)),
        ColorCategoria(id = 32, Color(0xFFe51951)),

        ColorCategoria(id = 33, Color(0xFFc0bff3)),
        ColorCategoria(id = 34, Color(0xFF9f9aeb)),
        ColorCategoria(id = 35, Color(0xFF7e71e1)),
        ColorCategoria(id = 36, Color(0xFF6952d5)),

        ColorCategoria(id = 37, Color(0xFFd8b6fc)),
        ColorCategoria(id = 38, Color(0xFFbf88f8)),
        ColorCategoria(id = 39, Color(0xFFa75af2)),
        ColorCategoria(id = 40, Color(0xFF8a2be2)),
    )

}