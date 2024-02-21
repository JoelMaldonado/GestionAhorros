package com.jjmf.android.gestionahorros.ui.navigation

sealed class Rutas(val route:String) {
    data object Login: Rutas("login")
    data object Splash: Rutas("splash")
    data object Menu: Rutas("menu")
    data object Inicio: Rutas("inicio")
    data object Cuentas: Rutas("cuentas")
    data object Categorias: Rutas("categorias")

    data object AddMovimiento: Rutas("add_movimiento")
}