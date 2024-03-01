package com.jjmf.android.gestionahorros.ui.navigation

sealed class Rutas(val route:String) {
    data object Login: Rutas("login")
    data object Splash: Rutas("splash")
    data object Menu: Rutas("Menu")
    data object Inicio: Rutas("Inicio")
    data object Cuentas: Rutas("Cuentas")
    data object Categorias: Rutas("Categorias")
    data object Preferencias: Rutas("Preferencias") {
        data object Idioma: Rutas("prefs_idioma")
    }

    data object AddMovimiento: Rutas("add_movimiento")
    data object AddCategoria: Rutas("add_categoria")
    data object AddCuenta: Rutas("add_cuenta")
    data object EditCuenta: Rutas("edit_cuenta?{id}"){
        fun senID(id:Int?) = "edit_cuenta?$id"
    }
}