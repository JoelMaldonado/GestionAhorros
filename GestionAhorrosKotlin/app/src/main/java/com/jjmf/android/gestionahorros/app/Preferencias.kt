package com.jjmf.android.gestionahorros.app

import android.content.Context

class Preferencias(val context: Context) {


    private val SHARED_NAME = "GestionAhorrosPrefs"
    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    private val KEY_TOKEN = "KEY_TOKEN"


    fun saveToken(valor:String) = storage.edit().putString(KEY_TOKEN, valor).apply()
    fun getToken() = storage.getString(KEY_TOKEN, null)
    fun removeToken() = storage.edit().remove(KEY_TOKEN)

}