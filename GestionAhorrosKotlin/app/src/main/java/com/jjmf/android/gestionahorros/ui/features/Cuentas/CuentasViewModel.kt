package com.jjmf.android.gestionahorros.ui.features.Cuentas

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.data.repository.CuentaRepository
import com.jjmf.android.gestionahorros.domain.model.Cuenta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CuentasViewModel @Inject constructor(
    private val repository: CuentaRepository
) : ViewModel() {

    var listCuentas by mutableStateOf<List<Cuenta>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    fun getListCuentas() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                when (val res = repository.getList()) {
                    is Result.Correcto -> listCuentas = res.datos ?: emptyList()
                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }
}