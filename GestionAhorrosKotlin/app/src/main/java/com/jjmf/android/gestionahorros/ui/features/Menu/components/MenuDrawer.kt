package com.jjmf.android.gestionahorros.ui.features.Menu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jjmf.android.gestionahorros.ui.navigation.Rutas


@Composable
fun MenuDrawer(
    close: (Rutas?) -> Unit
) {

    val tabSelected = remember { mutableStateOf(MenuTab.Inicio) }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .fillMaxHeight()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        MenuTab.entries.forEach { tab ->
            DrawerItem(
                icon = tab.icon,
                text = tab.name,
                selected = tabSelected.value == tab,
                click = {
                    close(tab.ruta)
                    tabSelected.value = tab
                }
            )
        }


    }
}