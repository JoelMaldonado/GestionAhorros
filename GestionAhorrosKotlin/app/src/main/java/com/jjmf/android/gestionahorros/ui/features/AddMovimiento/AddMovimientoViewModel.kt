package com.jjmf.android.gestionahorros.ui.features.AddMovimiento

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.data.repository.CategoriaRepository
import com.jjmf.android.gestionahorros.data.repository.CuentaRepository
import com.jjmf.android.gestionahorros.domain.model.Categoria
import com.jjmf.android.gestionahorros.domain.model.Cuenta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMovimientoViewModel @Inject constructor(
    private val repoCategoria: CategoriaRepository,
    private val repoCuenta: CuentaRepository
) : ViewModel() {
    var foto1 by mutableStateOf<Uri?>(null)
    var foto2 by mutableStateOf<Uri?>(null)
    var detalle by mutableStateOf("")
    var tipo by mutableStateOf(TipoMovimiento.Gasto)
    var monto by mutableStateOf("")

    var listCategorias by mutableStateOf<List<Categoria>>(emptyList())
    var listCuentas by mutableStateOf<List<Cuenta>>(emptyList())

    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun getListCategorias() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                when(val res = repoCategoria.getAll()){
                    is Result.Correcto -> listCategorias = res.datos ?: emptyList()
                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun getListCuentas() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                when(val res = repoCuenta.getList()){
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