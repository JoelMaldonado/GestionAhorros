package com.jjmf.android.gestionahorros.ui.features.Menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.android.gestionahorros.ui.features.AddMovimiento.AddMovimientoScreen
import com.jjmf.android.gestionahorros.ui.features.Categorias.CategoriasScreen
import com.jjmf.android.gestionahorros.ui.features.Cuentas.CuentasScreen
import com.jjmf.android.gestionahorros.ui.features.Inicio.InicioScreen
import com.jjmf.android.gestionahorros.ui.features.Menu.components.MenuDrawer
import com.jjmf.android.gestionahorros.ui.navigation.Rutas
import com.jjmf.android.gestionahorros.ui.theme.ColorP1
import com.jjmf.android.gestionahorros.ui.theme.ColorP2
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen() {

    val navMenu = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutine = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuDrawer(
                close = { ruta ->
                    coroutine.launch {
                        drawerState.close()
                    }
                    ruta?.let {
                        navMenu.navigate(it.route)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {


            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutine.launch { drawerState.open() }
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                },
                title = {
                    Text(text = "Inicio")
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = ColorP1,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )

            NavHost(navController = navMenu, startDestination = Rutas.Inicio.route) {
                composable(Rutas.Inicio.route) {
                    InicioScreen(
                        toAddRegistro = {
                            navMenu.navigate(Rutas.AddMovimiento.route)
                        }
                    )
                }
                composable(Rutas.Cuentas.route) { CuentasScreen() }
                composable(Rutas.Categorias.route) { CategoriasScreen() }
                composable(Rutas.AddMovimiento.route) { AddMovimientoScreen() }
            }
        }
    }
}