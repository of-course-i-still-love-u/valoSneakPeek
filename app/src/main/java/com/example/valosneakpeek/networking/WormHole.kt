package com.example.valosneakpeek.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WormHole {

    private val loggerInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY

        }
    }

    fun <T> createWormHole(aClass: Class<T>): T {
        val httpBuilder = OkHttpClient.Builder().apply {
            addInterceptor(loggerInterceptor)
        }

        val httpClient = httpBuilder
            .readTimeout(10L, TimeUnit.MINUTES)
            .connectTimeout(10L,TimeUnit.MINUTES)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ServerApi.getBaseApiServer())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
        return retrofit.create(aClass)

    }


}