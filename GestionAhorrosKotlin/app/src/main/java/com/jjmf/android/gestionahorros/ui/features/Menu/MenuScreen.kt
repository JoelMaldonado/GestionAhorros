package com.jjmf.android.gestionahorros.ui.features.Menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jjmf.android.gestionahorros.ui.features.AddCategoria.AddCategoriaScreen
import com.jjmf.android.gestionahorros.ui.features.AddCuenta.AddCuentaScreen
import com.jjmf.android.gestionahorros.ui.features.AddMovimiento.AddMovimientoScreen
import com.jjmf.android.gestionahorros.ui.features.Categorias.CategoriasScreen
import com.jjmf.android.gestionahorros.ui.features.Cuentas.CuentasScreen
import com.jjmf.android.gestionahorros.ui.features.EditCuenta.EditCuentaScreen
import com.jjmf.android.gestionahorros.ui.features.Inicio.InicioScreen
import com.jjmf.android.gestionahorros.ui.features.Menu.components.MenuTab
import com.jjmf.android.gestionahorros.ui.features.Preferencias.PreferenciasScreen
import com.jjmf.android.gestionahorros.ui.features.Preferencias.screens.Idioma.IdiomaScreen
import com.jjmf.android.gestionahorros.ui.navigation.Rutas

@Composable
fun MenuScreen() {

    val tab = remember { mutableStateOf(MenuTab.Inicio) }

    val title = remember {
        mutableStateOf("Inicio")
    }

    val navMenu = rememberNavController()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        NavHost(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            navController = navMenu,
            startDestination = Rutas.Inicio.route
        ) {
            composable(Rutas.Inicio.route) {
                InicioScreen(
                    toAddRegistro = {
                        navMenu.navigate(Rutas.AddMovimiento.route)
                        title.value = "Añadir Registro"
                    }
                )
            }
            composable(Rutas.Cuentas.route) {
                CuentasScreen(
                    toAddCuenta = {
                        navMenu.navigate(Rutas.AddCuenta.route)
                        title.value = "Añadir Cuenta"
                    },
                    toEdit = {
                        navMenu.navigate(Rutas.EditCuenta.senID(it))
                        title.value = "Editar Cuenta"
                    }
                )
            }
            composable(Rutas.Categorias.route) {
                CategoriasScreen(
                    toAddCategoria = {
                        navMenu.navigate(Rutas.AddCategoria.route)
                        title.value = "Añadir Categoria"
                    }
                )
            }
            composable(Rutas.Preferencias.route) {
                PreferenciasScreen(
                    navMenu
                )
            }

            /** Screens Configuración **/
            composable(Rutas.Preferencias.Idioma.route){
                IdiomaScreen(
                    update = {
                        navMenu.popBackStack()
                        navMenu.navigate(Rutas.Preferencias.Idioma.route)
                    },
                    back = {
                        navMenu.popBackStack()
                    }
                )
            }

            composable(Rutas.AddMovimiento.route) {
                AddMovimientoScreen()
            }
            composable(
                route = Rutas.AddCategoria.route
            ) {
                AddCategoriaScreen(
                    back = {
                        navMenu.popBackStack()
                    }
                )
            }

            composable(
                route = Rutas.AddCuenta.route
            ) {
                AddCuentaScreen(
                    back = {
                        navMenu.popBackStack()
                    }
                )
            }

            composable(
                route = Rutas.EditCuenta.route,
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )
            ) {
                it.arguments?.getInt("id")?.let { id ->
                    EditCuentaScreen(
                        id = id,
                        back = {
                            navMenu.popBackStack()
                        }
                    )
                }
            }

        }

        NavigationBar {
            NavigationBarItem(
                selected = tab.value == MenuTab.Inicio,
                onClick = {
                    tab.value = MenuTab.Inicio
                    navMenu.navigate(Rutas.Inicio.route)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = "Inicio")
                }
            )
            NavigationBarItem(
                selected =
                tab.value == MenuTab.Cuentas,
                onClick = {
                    tab.value = MenuTab.Cuentas
                    navMenu.navigate(Rutas.Cuentas.route)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = "Buscar")
                }
            )
            NavigationBarItem(
                selected = tab.value == MenuTab.Preferencias,
                onClick = {

                    tab.value = MenuTab.Preferencias
                    navMenu.navigate(Rutas.Preferencias.route)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = "Preferencias")
                }
            )
        }
    }

}