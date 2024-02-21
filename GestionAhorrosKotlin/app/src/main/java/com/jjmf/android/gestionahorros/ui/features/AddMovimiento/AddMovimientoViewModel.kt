package com.jjmf.android.gestionahorros.ui.features.AddMovimiento

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddMovimientoViewModel @Inject constructor(

) : ViewModel() {
    var tipo by mutableStateOf(TipoMovimiento.Gasto)
    var monto by mutableStateOf("")

}