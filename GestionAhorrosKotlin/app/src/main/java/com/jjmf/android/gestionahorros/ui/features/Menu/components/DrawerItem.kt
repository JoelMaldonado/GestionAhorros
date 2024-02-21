package com.jjmf.android.gestionahorros.ui.features.Menu.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


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
        }
    )
}
