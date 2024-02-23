package com.jjmf.android.gestionahorros.ui.features.Splash

import android.util.Log
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var isSesionActive by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun verificarSesion() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val res = repository.token()) {
                    is Result.Correcto -> {
                        isSesionActive = true
                        Log.d("tagito", res.datos.toString())
                    }
                    is Result.Error -> {
                        Log.d("tagito", res.mensaje.toString())
                        error = res.mensaje
                    }
                }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }
}