package com.jjmf.android.gestionahorros.ui.features.Cuentas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.gestionahorros.ui.theme.ColorP1
import com.jjmf.android.gestionahorros.ui.theme.ColorS1
import com.jjmf.android.gestionahorros.ui.theme.TextSubTitle
import com.jjmf.android.gestionahorros.ui.theme.TextTitle
import com.jjmf.android.gestionahorros.util.show

@Composable
fun CuentasScreen(
    toEdit: (id: Int?) -> Unit,
    toAddCuenta: () -> Unit,
    viewModel: CuentasViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getListCuentas()
    }

    val context = LocalContext.current

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = null,
                    tint = ColorP1
                )
            }

            Text(text = "Cuentas", style = TextSubTitle, color = ColorP1)
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                columns = GridCells.Fixed(4),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    viewModel.listCuentas
                ) {

                    Card(
                        modifier = Modifier.size(80.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = it.color.color,
                            contentColor = Color.White
                        ),
                        onClick = {
                            toEdit(it.id)
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = it.icono.icon,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = it.nombre.toString().capitalize(Locale.current),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

            }

            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = toAddCuenta,
                containerColor = ColorS1
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}