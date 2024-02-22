package com.jjmf.android.gestionahorros.ui.features.Login

import android.util.Log
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.gestionahorros.app.BaseApp.Companion.prefs
import com.jjmf.android.gestionahorros.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var usuario by mutableStateOf("")
    var clave by mutableStateOf("")

    var toMenu by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                delay(1000)
                val res = repository.login(usuario, clave)
                if (res.isSuccess){
                    res.getOrNull()?.let {
                        prefs.saveToken(it)
                        toMenu = true
                    } ?: run {
                        Log.d("tagito", res.getOrThrow())
                        error = res.getOrThrow()
                    }
                }
            } catch (e: Exception) {
                Log.d("tagito", e.message.toString())
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }
}