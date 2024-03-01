package com.jjmf.android.gestionahorros.ui.features.Inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.gestionahorros.ui.theme.ColorFondo
import com.jjmf.android.gestionahorros.ui.theme.ColorP1
import com.jjmf.android.gestionahorros.ui.theme.ColorP5
import com.jjmf.android.gestionahorros.ui.theme.TextTitle
import java.text.NumberFormat
import java.util.Locale

@Composable
fun InicioScreen(
    toAddRegistro: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Bienvenido!",
                style = TextTitle
            )
            FloatingActionButton(
                modifier = Modifier.size(45.dp),
                onClick = toAddRegistro,
                containerColor = ColorP1,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "S/ 2,059.00", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ShowChart,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = Color.Red
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Green.copy(0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowDownward,
                                contentDescription = null,
                                tint = Color.Green
                            )
                        }
                        Text(
                            text = buildAnnotatedString {
                                withStyle(SpanStyle(fontSize = 12.sp)) {
                                    append("Ingresos\n")
                                }
                                withStyle(SpanStyle()) {
                                    append("S/ 2,039")
                                }
                            },
                            lineHeight = 16.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Red.copy(0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowUpward,
                                contentDescription = null,
                                tint = Color.Red
                            )
                        }
                        Text(
                            text = buildAnnotatedString {
                                withStyle(SpanStyle(fontSize = 12.sp)) {
                                    append("Gastos\n")
                                }
                                withStyle(SpanStyle()) {
                                    append("S/ 2,039")
                                }
                            },
                            lineHeight = 16.sp
                        )
                    }
                }

            }
        }

        repeat(4) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(ColorP5),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 5.dp),
                        text = buildAnnotatedString {
                            withStyle(SpanStyle()) {
                                append("Iphone 15 Pro Max\n")
                            }
                            withStyle(SpanStyle(fontSize = 12.sp, color = Color.Gray)) {
                                append("Compra\n")
                            }
                            withStyle(SpanStyle(fontSize = 14.sp)) {
                                append("29 Febrero 2024")
                            }
                        },
                        lineHeight = 18.sp
                    )
                    Text(text = currencyNumbertoString(5199.5430))
                }
            }
        }

    }
}

fun currencyNumbertoString(price: Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale("es", "PE"))
    return format.format(price)
}