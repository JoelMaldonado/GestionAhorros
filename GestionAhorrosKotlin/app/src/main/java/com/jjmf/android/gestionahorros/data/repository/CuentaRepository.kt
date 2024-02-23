package com.jjmf.android.gestionahorros.data.repository

import com.jjmf.android.gestionahorros.core.Result
import com.jjmf.android.gestionahorros.domain.model.Cuenta

interface CuentaRepository {

suspend fun getList():Result<List<Cuenta>>

}