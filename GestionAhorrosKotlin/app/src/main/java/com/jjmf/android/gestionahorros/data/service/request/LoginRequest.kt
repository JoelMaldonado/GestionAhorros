package com.jjmf.android.gestionahorros.data.service.request

import com.google.gson.annotations.SerializedName


data class LoginRequest(
    @SerializedName("correo") val correo: String,
    @SerializedName("password") val password: String
)

data class Wrapper<T>(
    @SerializedName("isSuccess") val isSuccess: Boolean?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: T
)

data class LoginResponse(
    @SerializedName("token") val token: String?
)