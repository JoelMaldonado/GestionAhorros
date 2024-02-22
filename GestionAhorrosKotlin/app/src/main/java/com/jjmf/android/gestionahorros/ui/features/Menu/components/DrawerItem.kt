package com.jjmf.android.gestionahorros.ui.features.Menu.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.jjmf.android.gestionahorros.ui.theme.ColorP1
import com.jjmf.android.gestionahorros.ui.theme.ColorP2
import com.jjmf.android.gestionahorros.ui.theme.ColorP3
import com.jjmf.android.gestionahorros.ui.theme.ColorP4
import com.jjmf.android.gestionahorros.ui.theme.ColorP8
import com.jjmf.android.gestionahorros.ui.theme.ColorS1
import com.jjmf.android.gestionahorros.ui.theme.ColorS4


@Composable
fun DrawerItem(
    icon: ImageVector,
    text: String,
    selected:Boolean,
    click: () -> Unit
) {
    NavigationDrawerItem(
        label = {
            Text(text = text)
        },
        selected = selected,
        onClick = click,
        icon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        badge = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        },
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = ColorP3,
            unselectedContainerColor = ColorP8,
            selectedTextColor = Color.White,
            selectedIconColor = Color.White
        )
    )
}
