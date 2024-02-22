package com.jjmf.android.gestionahorros.data.service.request

import com.google.gson.annotations.SerializedName


data class LoginRequest(
    @SerializedName("correo") val correo: String,
    @SerializedName("password") val password: String
)

data class LoginResponse(
    @SerializedName("token") val token: String?
)