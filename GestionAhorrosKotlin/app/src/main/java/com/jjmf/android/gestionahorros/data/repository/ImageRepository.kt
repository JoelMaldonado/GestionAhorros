package com.jjmf.android.gestionahorros.data.repository

import android.net.Uri
import com.jjmf.android.gestionahorros.core.Result

interface ImageRepository {

    suspend fun insert(uri:Uri) : Result<String>

}