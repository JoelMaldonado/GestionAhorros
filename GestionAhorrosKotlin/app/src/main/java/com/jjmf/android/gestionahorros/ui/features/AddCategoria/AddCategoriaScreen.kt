package com.jjmf.android.gestionahorros.ui.features.AddCategoria

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.gestionahorros.ui.features.AddCategoria.components.SheetSelectColor
import com.jjmf.android.gestionahorros.ui.features.AddCategoria.components.SheetSelectIcono
import com.jjmf.android.gestionahorros.ui.features.AddMovimiento.TipoMovimiento
import com.jjmf.android.gestionahorros.ui.theme.ColorP7
import com.jjmf.android.gestionahorros.util.show

@Composable
fun AddCategoriaScreen(
    back: () -> Unit,
    viewModel: AddCategoriaViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    if (viewModel.back) {
        back()
        viewModel.back = false
    }

    if (viewModel.sheetSelectIcono) {
        SheetSelectIcono(
            close = {
                viewModel.sheetSelectIcono = false
            },
            click = {
                viewModel.icono = it
            }
        )
    }

    if (viewModel.sheetSelectColor) {
        SheetSelectColor(
            close = {
                viewModel.sheetSelectColor = false
            },
            click = {
                viewModel.color = it
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        OutlinedTextField(value = viewModel.nombre,
            onValueChange = { viewModel.nombre = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "Nombre"
                )
            })
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            RadioButton(selected = viewModel.tipo == TipoMovimiento.Ingreso,
                onClick = { viewModel.tipo = TipoMovimiento.Ingreso })
            Text(text = "Ingreso")
            Spacer(modifier = Modifier.weight(1f))
            RadioButton(selected = viewModel.tipo == TipoMovimiento.Gasto,
                onClick = { viewModel.tipo = TipoMovimiento.Gasto })
            Text(text = "Gasto")
            Spacer(modifier = Modifier.weight(1f))
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(ColorP7)
                        .clickable {
                            viewModel.sheetSelectIcono = true
                        }, contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = viewModel.icono.icon,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Text(text = "Icono")
            }
            Column(
                modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(viewModel.color.color)
                    .clickable {
                        viewModel.sheetSelectColor = true
                    })
                Text(text = "Color")
            }

        }

        Spacer(modifier = Modifier.weight(1f))

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = viewModel::save
            ) {
                Text(text = "Guardar")
            }
        }
    }

}
