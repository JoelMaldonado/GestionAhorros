package com.jjmf.android.gestionahorros.ui.features.Categorias

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.data.repository.CategoriaRepository
import com.jjmf.android.gestionahorros.domain.model.Categoria
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriasViewModel @Inject constructor(
private val repository: CategoriaRepository
) : ViewModel() {

    var list by mutableStateOf<List<Categoria>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    fun listarCategorias() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                isLoading = true
                when(val res = repository.getAll()){
                    is Result.Correcto -> list = res.datos ?: emptyList()
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