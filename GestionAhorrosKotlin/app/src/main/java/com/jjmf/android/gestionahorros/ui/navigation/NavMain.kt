package com.jjmf.android.gestionahorros.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.android.gestionahorros.ui.features.Menu.MenuScreen

@Composable
fun NavMain() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutas.Menu.route) {
        composable(Rutas.Menu.route) {
            MenuScreen()
        }
    }
}