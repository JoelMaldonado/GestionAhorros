package com.jjmf.android.gestionahorros.ui.features.AddCategoria.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.AirplanemodeActive
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.CardGiftcard
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocalGasStation
import androidx.compose.material.icons.outlined.LocalLaundryService
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.LocalTaxi
import androidx.compose.material.icons.outlined.PedalBike
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material.icons.outlined.Train
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material.icons.outlined.Watch
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.gestionahorros.domain.model.Icono
import com.jjmf.android.gestionahorros.ui.theme.ColorP8
import com.jjmf.android.gestionahorros.ui.theme.ColorTitulo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetSelectIcono(
    click:(Icono)->Unit,
    close:()->Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
                           close()
        },
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 30.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Selecciona un icono",
                color = ColorTitulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                content = {
                    items(getIconos()) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(ColorP8)
                                .clickable {
                                    click(it)
                                    close()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                })
        }
    }
}

fun getIconos(): List<Icono> {
    return listOf(
        Icono(id = 1, icon = Icons.Outlined.AccountBalance),
        Icono(id = 2, icon = Icons.Outlined.CreditCard),
        Icono(id = 3, icon = Icons.Outlined.Calculate),
        Icono(id = 4, icon = Icons.Outlined.Store),
        Icono(id = 5, icon = Icons.Outlined.AttachMoney),
        Icono(id = 6, icon = Icons.Outlined.BarChart),
        Icono(id = 7, icon = Icons.Outlined.Language),
        Icono(id = 8, icon = Icons.Outlined.Wallet),
        Icono(id = 9, icon = Icons.Outlined.Language),
        Icono(id = 10, icon = Icons.Outlined.DirectionsBus),
        Icono(id = 11, icon = Icons.Outlined.LocalShipping),
        Icono(id = 12, icon = Icons.Outlined.PedalBike),
        Icono(id = 13, icon = Icons.Outlined.LocalTaxi),
        Icono(id = 14, icon = Icons.Outlined.AirplanemodeActive),
        Icono(id = 15, icon = Icons.Outlined.Train),
        Icono(id = 16, icon = Icons.Outlined.LocalGasStation),
        Icono(id = 17, icon = Icons.Outlined.AddShoppingCart),
        Icono(id = 18, icon = Icons.Outlined.CameraAlt),
        Icono(id = 19, icon = Icons.Outlined.LocalLaundryService),
        Icono(id = 20, icon = Icons.Outlined.ShoppingBag),
        Icono(id = 21, icon = Icons.Outlined.Watch),
        Icono(id = 22, icon = Icons.Outlined.CardGiftcard),
        Icono(id = 23, icon = Icons.Outlined.PhoneAndroid)
    )
}