package com.jjmf.android.gestionahorros.data.service

import com.jjmf.android.gestionahorros.data.dto.CategoriaDto
import com.jjmf.android.gestionahorros.data.dto.CuentaDto
import com.jjmf.android.gestionahorros.data.service.request.Wrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CuentaService {

    @GET("cuenta")
    suspend fun getList(): Response<Wrapper<List<CuentaDto>>>

    @POST("cuenta")
    suspend fun insert(@Body request: CuentaDto) : Response<Wrapper<Any>>
}