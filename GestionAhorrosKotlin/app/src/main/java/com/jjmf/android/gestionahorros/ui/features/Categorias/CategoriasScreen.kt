package com.jjmf.android.gestionahorros.ui.features.Categorias

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.gestionahorros.ui.features.AddMovimiento.TipoMovimiento
import com.jjmf.android.gestionahorros.ui.theme.ColorS1

@Composable
fun CategoriasScreen(
    toAddCategoria: () -> Unit,
    viewModel: CategoriasViewModel = hiltViewModel()
) {

    val tab = remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = Unit) {
        viewModel.listarCategorias()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            Column {
                TabRow(selectedTabIndex = tab.intValue) {


                    Tab(
                        selected = tab.intValue == 0,
                        onClick = {
                            tab.intValue = 0
                        }
                    ) {
                        Text(text = "Gastos", modifier = Modifier.padding(16.dp))
                    }

                    Tab(
                        selected = tab.intValue == 1,
                        onClick = {
                            tab.intValue = 1
                        }
                    ) {
                        Text(text = "Ingresos")
                    }
                }
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        viewModel.list.filter { if (tab.intValue == 0) it.tipo == TipoMovimiento.Gasto else it.tipo == TipoMovimiento.Ingreso }
                    ) {
                        Card(
                            modifier = Modifier.size(80.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = it.color.color,
                                contentColor = Color.White
                            )
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
            }
        }

        FloatingActionButton(
            onClick = toAddCategoria,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = ColorS1
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

    }
}