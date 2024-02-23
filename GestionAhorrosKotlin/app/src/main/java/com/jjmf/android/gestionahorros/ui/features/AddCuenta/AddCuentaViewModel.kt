package com.jjmf.android.gestionahorros.ui.features.AddCuenta

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.data.repository.CuentaRepository
import com.jjmf.android.gestionahorros.domain.model.Categoria
import com.jjmf.android.gestionahorros.domain.model.ColorCategoria
import com.jjmf.android.gestionahorros.domain.model.Icono
import com.jjmf.android.gestionahorros.ui.features.AddMovimiento.TipoMovimiento
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCuentaViewModel @Inject constructor(
    private val repository: CuentaRepository
) : ViewModel() {


    var color by mutableStateOf(ColorCategoria(id = 1, Color(0xFF8B0000)))
    var icono by mutableStateOf(Icono(id = 1, icon = Icons.Outlined.AccountBalance))
    var sheetSelectIcono by mutableStateOf(false)
    var sheetSelectColor by mutableStateOf(false)


    var nombre by mutableStateOf("")

    var back by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun save() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                isLoading = true

            }catch (e:Exception){
                error = e.message
            }finally {
                isLoading = false
            }
        }
    }
}