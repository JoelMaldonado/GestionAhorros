package com.jjmf.android.gestionahorros.data.module

import com.jjmf.android.gestionahorros.data.repository.AuthRepository
import com.jjmf.android.gestionahorros.domain.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun repoAuth(impl: AuthRepositoryImpl): AuthRepository
}