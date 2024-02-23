package com.jjmf.android.gestionahorros.data.module

import com.jjmf.android.gestionahorros.app.BaseApp.Companion.prefs
import com.jjmf.android.gestionahorros.data.service.ApiService
import com.jjmf.android.gestionahorros.data.service.CategoriaService
import com.jjmf.android.gestionahorros.data.service.CuentaService
import com.jjmf.android.gestionahorros.util.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttp(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(interceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        val token = prefs.getToken()

        okHttpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoriaService(retrofit: Retrofit): CategoriaService {
        return retrofit.create(CategoriaService::class.java)
    }

    @Singleton
    @Provides
    fun provideCuentaService(retrofit: Retrofit): CuentaService {
        return retrofit.create(CuentaService::class.java)
    }

}