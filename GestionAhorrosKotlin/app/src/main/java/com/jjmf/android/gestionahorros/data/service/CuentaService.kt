package com.jjmf.android.gestionahorros.data.service

import com.jjmf.android.gestionahorros.data.dto.CuentaDto
import com.jjmf.android.gestionahorros.data.service.request.Wrapper
import retrofit2.Response
import retrofit2.http.GET

interface CuentaService {

    @GET("cuenta")
    suspend fun getList(): Response<Wrapper<List<CuentaDto>>>

}