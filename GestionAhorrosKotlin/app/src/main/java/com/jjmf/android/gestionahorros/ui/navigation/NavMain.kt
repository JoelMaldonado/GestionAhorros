package com.jjmf.android.gestionahorros.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.android.gestionahorros.ui.features.Login.LoginScreen
import com.jjmf.android.gestionahorros.ui.features.Menu.MenuScreen
import com.jjmf.android.gestionahorros.ui.features.Splash.SplashScreen

@Composable
fun NavMain() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutas.Splash.route) {
        composable(Rutas.Splash.route) {
            SplashScreen(
                toLogin = {
                    navController.navigate(Rutas.Login.route)
                },
                toMenu = {
                    navController.navigate(Rutas.Menu.route)
                }
            )
        }
        composable(Rutas.Login.route) {
            LoginScreen(
                toMenu = {
                    navController.navigate(Rutas.Menu.route)
                }
            )
        }
        composable(Rutas.Menu.route) {
            MenuScreen()
        }
    }
}