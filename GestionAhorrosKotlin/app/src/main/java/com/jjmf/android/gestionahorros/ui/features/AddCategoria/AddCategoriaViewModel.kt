package com.jjmf.android.gestionahorros.ui.features.AddCategoria

import android.media.Image
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
import androidx.compose.material.icons.outlined.Home
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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.data.repository.CategoriaRepository
import com.jjmf.android.gestionahorros.domain.model.Categoria
import com.jjmf.android.gestionahorros.domain.model.ColorCategoria
import com.jjmf.android.gestionahorros.domain.model.Icono
import com.jjmf.android.gestionahorros.ui.features.AddMovimiento.TipoMovimiento
import com.jjmf.android.gestionahorros.ui.theme.ColorP1
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoriaViewModel @Inject constructor(
    private val repository: CategoriaRepository
) : ViewModel() {



    var color by mutableStateOf(ColorCategoria(id = 1, Color(0xFF8B0000)))
    var icono by mutableStateOf(Icono(id = 1, icon = Icons.Outlined.AccountBalance))
    var sheetSelectIcono by mutableStateOf(false)
    var sheetSelectColor by mutableStateOf(false)


    var nombre by mutableStateOf("")
    var tipo by mutableStateOf(TipoMovimiento.Ingreso)

    var back by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun save() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                isLoading = true
                val cat = Categoria(
                    id = null,
                    nombre = nombre,
                    tipo = tipo,
                    color = color,
                    icono = icono,
                    activo = true
                )
                val res = repository.insert(cat)
                when(res){
                    is Result.Correcto -> back = true
                    is Result.Error -> error = res.mensaje
                }
            }catch (e:Exception){
                error = e.message
            }finally {
                isLoading = false
            }
        }
    }
}