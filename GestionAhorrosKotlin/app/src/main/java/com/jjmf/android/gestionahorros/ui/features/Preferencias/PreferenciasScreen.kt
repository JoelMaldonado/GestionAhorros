package com.jjmf.android.gestionahorros.ui.features.Preferencias

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jjmf.android.gestionahorros.R
import com.jjmf.android.gestionahorros.ui.features.Preferencias.components.ItemConfig
import com.jjmf.android.gestionahorros.ui.navigation.Rutas
import com.jjmf.android.gestionahorros.ui.theme.ColorFondo
import com.jjmf.android.gestionahorros.ui.theme.ColorP1
import com.jjmf.android.gestionahorros.ui.theme.ColorP3
import com.jjmf.android.gestionahorros.ui.theme.ColorS1
import com.jjmf.android.gestionahorros.ui.theme.ColorTitulo
import java.util.Currency
import java.util.Locale

@Composable
fun PreferenciasScreen(
    navMenu: NavHostController,
    viewModel: PreferenciasViewModel = hiltViewModel()
) {

    val d = try {
        Currency.getInstance(Locale.getDefault()).displayName
    }catch (e:Exception){
        "Error"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(id = R.string.select_opcion),
            fontSize = 12.sp,
            color = Color.Gray
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {

            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                ItemConfig(
                    icon = Icons.Default.Wallet,
                    text = stringResource(id = R.string.cuentas),
                    color = ColorP1,
                    click = {
                        navMenu.navigate(Rutas.Cuentas.route)
                    }
                )
                ItemConfig(
                    icon = Icons.Default.ShoppingCart,
                    text = stringResource(id = R.string.categorias),
                    color = ColorS1,
                    click = {
                        navMenu.navigate(Rutas.Categorias.route)
                    }
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {

            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                ItemConfig(
                    icon = Icons.Default.Language,
                    text = stringResource(id = R.string.idioma),
                    label = Currency.getInstance(Locale.getDefault()).symbol,
                    color = ColorP3,
                    click = {
                        navMenu.navigate(Rutas.Preferencias.Idioma.route)
                    }
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {

            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ItemConfig(
                    icon = Icons.AutoMirrored.Filled.Chat,
                    text = stringResource(id = R.string.centro_mensajes),
                    color = ColorTitulo,
                    click = {
                    }
                )
                ItemConfig(
                    icon = Icons.AutoMirrored.Filled.Help,
                    text = stringResource(id = R.string.ayuda),
                    color = ColorP3,
                    click = {

                    }
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {

            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null, tint = ColorP1
                )
                Text(
                    text = stringResource(id = R.string.eliminar_cuenta)
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {

            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = null, tint =
                    ColorP1
                )
                Text(
                    text = stringResource(id = R.string.cerrar_sesion)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "V1.0",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 12.sp,
            color = Color.DarkGray
        )
    }
}
