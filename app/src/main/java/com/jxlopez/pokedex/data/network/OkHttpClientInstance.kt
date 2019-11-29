package com.jxlopez.pokedex.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT:Long = 10000
private const val DATA_RETRIEVAL_TIMEOUT:Long = 30000

object OkHttpClientInstance {
    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    fun build(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val requestBuilder = request.newBuilder()
                val response = chain.proceed(requestBuilder.build())
                response
            }
            .callTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(DATA_RETRIEVAL_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .build()
    }
}