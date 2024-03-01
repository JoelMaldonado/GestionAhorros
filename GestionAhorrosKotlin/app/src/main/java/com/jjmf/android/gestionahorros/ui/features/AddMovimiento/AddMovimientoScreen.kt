package com.jjmf.android.gestionahorros.ui.features.AddMovimiento

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jjmf.android.gestionahorros.ui.features.AddMovimiento.components.SelectCategoria
import com.jjmf.android.gestionahorros.ui.features.AddMovimiento.components.SelectCuenta
import com.jjmf.android.gestionahorros.util.show

@Composable
fun AddMovimientoScreen(
    viewModel: AddMovimientoViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val uri = remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            if (it && uri.value != null) {
                viewModel.foto = uri.value
            }
        }
    )

    val cameraPermiso = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            when (it) {
                true -> {
                    uri.value = context.contentResolver.insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        ContentValues()
                    )
                    cameraLauncher.launch(uri.value)
                }

                false -> {
                    context.show("Debes aceptar los permisos de la camara")
                }
            }
        }
    )


    val isFocused = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getListCategorias()
        viewModel.getListCuentas()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            RadioButton(
                selected = viewModel.tipo == TipoMovimiento.Ingreso,
                onClick = {
                    viewModel.tipo = TipoMovimiento.Ingreso
                }
            )
            Text(text = "Ingreso")

            Spacer(modifier = Modifier.weight(1f))

            RadioButton(
                selected = viewModel.tipo == TipoMovimiento.Gasto,
                onClick = {
                    viewModel.tipo = TipoMovimiento.Gasto
                }
            )

            Text(text = "Gasto")

            Spacer(modifier = Modifier.weight(1f))

        }

        Box(
            modifier = Modifier.fillMaxWidth(0.3f),
            contentAlignment = Alignment.Center
        ) {
            if (viewModel.monto.isEmpty() && !isFocused.value) {
                Text(text = "0", fontSize = 30.sp)
            }
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    },
                value = viewModel.monto,
                onValueChange = { newValue ->
                    viewModel.monto = newValue
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                textStyle = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                ),
                singleLine = true
            )
            HorizontalDivider(
                modifier = Modifier.align(Alignment.BottomCenter),
                thickness = 3.dp
            )

        }

        SelectCuenta(
            list = viewModel.listCuentas
        )

        SelectCategoria(
            list = viewModel.listCategorias.filter { it.tipo == viewModel.tipo }
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Detalle", color = Color.Gray, fontSize = 14.sp)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.detalle,
                onValueChange = { viewModel.detalle = it },
                label = {
                    Text(text = "Ingrese un detalle")
                }
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Fotos", color = Color.Gray, fontSize = 14.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                        .clickable {
                            if (
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CAMERA
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                uri.value = context.contentResolver.insert(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    ContentValues()
                                )
                                cameraLauncher.launch(uri.value)
                            } else {
                                cameraPermiso.launch(Manifest.permission.CAMERA)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    viewModel.foto?.let { uri ->
                        AsyncImage(
                            model = uri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } ?: run {
                        Icon(imageVector = Icons.Default.Image, contentDescription = null)
                    }
                }
            }
        }

        Button(
            onClick = viewModel::save
        ) {
            Text(text = "Guardar")
        }
    }
}
