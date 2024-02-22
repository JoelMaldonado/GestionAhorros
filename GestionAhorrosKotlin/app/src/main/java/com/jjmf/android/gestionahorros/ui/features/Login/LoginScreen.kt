package com.jjmf.android.gestionahorros.ui.features.Login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.gestionahorros.ui.theme.ColorP1
import com.jjmf.android.gestionahorros.util.show

@Composable
fun LoginScreen(
    toMenu:()->Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    if (viewModel.toMenu){
        LaunchedEffect(key1 = Unit){
            toMenu()
            viewModel.toMenu = false
        }
    }

    val context = LocalContext.current

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Login",
            color = ColorP1,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = viewModel.usuario,
            onValueChange = { viewModel.usuario = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Usuario")
            }
        )

        OutlinedTextField(
            value = viewModel.clave,
            onValueChange = { viewModel.clave = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Contraseña")
            },
            trailingIcon = {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(imageVector = Icons.Default.Visibility, contentDescription = null)
                }
            }
        )

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = viewModel::login
            ) {
                Text(text = "Ingresar")
            }
        }

    }

}

