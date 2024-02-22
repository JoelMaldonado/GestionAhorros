package com.jjmf.android.gestionahorros.core

sealed class Result<out T>{
    data class Correcto<T> (val datos:T? ) : Result<T>()
    data class Error(val mensaje :String?) : Result<Nothing>()
}