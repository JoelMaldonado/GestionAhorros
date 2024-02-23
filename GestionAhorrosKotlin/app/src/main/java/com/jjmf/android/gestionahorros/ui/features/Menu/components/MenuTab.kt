package com.jjmf.android.gestionahorros.ui.features.Menu.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.ui.graphics.vector.ImageVector
import com.jjmf.android.gestionahorros.ui.navigation.Rutas

enum class MenuTab(val icon: ImageVector, val ruta: Rutas?) {
    Inicio(icon = Icons.Outlined.Home, ruta = Rutas.Inicio),
    Cuentas(icon = Icons.Outlined.CreditCard, ruta = Rutas.Cuentas),
    Categorias(icon = Icons.Outlined.ShoppingBag, ruta = Rutas.Categorias),
    Preferencias(icon = Icons.Outlined.Settings, ruta = Rutas.Preferencias)
}
