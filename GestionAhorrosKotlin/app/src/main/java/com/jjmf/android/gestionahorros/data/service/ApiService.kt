package com.jjmf.android.gestionahorros.data.service

import com.jjmf.android.gestionahorros.data.dto.UsuarioDto
import com.jjmf.android.gestionahorros.data.service.request.LoginRequest
import com.jjmf.android.gestionahorros.data.service.request.LoginResponse
import com.jjmf.android.gestionahorros.data.service.request.Wrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<Wrapper<LoginResponse>>

    @GET("auth/token")
    suspend fun token(): Response<Wrapper<UsuarioDto>>
}
