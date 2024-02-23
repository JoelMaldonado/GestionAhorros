package com.jjmf.android.gestionahorros.data.service

import com.jjmf.android.gestionahorros.data.dto.CategoriaDto
import com.jjmf.android.gestionahorros.data.service.request.Wrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoriaService {

    @GET("categoria")
    suspend fun getList(): Response<Wrapper<List<CategoriaDto>>>

    @POST("categoria")
    suspend fun insert(@Body request: CategoriaDto) : Response<Wrapper<Any>>

}