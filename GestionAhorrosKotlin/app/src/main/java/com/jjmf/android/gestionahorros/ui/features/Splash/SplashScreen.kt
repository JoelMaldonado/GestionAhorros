package com.jjmf.android.gestionahorros.ui.features.Splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.gestionahorros.ui.theme.ColorP1
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    toLogin: () -> Unit,
    toMenu: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.verificarSesion()
    }

    LaunchedEffect(key1 = Unit){
        delay(500)
        if (viewModel.isSesionActive) {
            toMenu()
            viewModel.isSesionActive = false
        } else {
            toLogin()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorP1),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "SPLASH", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)

    }


}