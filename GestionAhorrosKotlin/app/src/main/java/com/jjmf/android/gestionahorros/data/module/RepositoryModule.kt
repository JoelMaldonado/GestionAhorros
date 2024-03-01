package com.jjmf.android.gestionahorros.data.module

import com.jjmf.android.gestionahorros.data.repository.AuthRepository
import com.jjmf.android.gestionahorros.data.repository.CategoriaRepository
import com.jjmf.android.gestionahorros.data.repository.CuentaRepository
import com.jjmf.android.gestionahorros.data.repository.ImageRepository
import com.jjmf.android.gestionahorros.domain.repository.AuthRepositoryImpl
import com.jjmf.android.gestionahorros.domain.repository.CategoriaRepositoryImpl
import com.jjmf.android.gestionahorros.domain.repository.CuentaRepositoryImpl
import com.jjmf.android.gestionahorros.domain.repository.ImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun repoAuth(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun repoCategoria(impl: CategoriaRepositoryImpl): CategoriaRepository

    @Binds
    abstract fun repoCuenta(impl: CuentaRepositoryImpl): CuentaRepository
    @Binds
    abstract fun repoImage(impl: ImageRepositoryImpl): ImageRepository

}