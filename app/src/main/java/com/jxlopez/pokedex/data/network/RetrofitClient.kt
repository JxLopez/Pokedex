package com.jxlopez.pokedex.data.network

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jxlopez.pokedex.BuildConfig


object RetrofitClient {

     private var retrofit: Retrofit? = null
     private val builder = GsonBuilder()
         .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
         .create()

     fun getClient(): Retrofit? {
         if (retrofit == null) {
             var client = OkHttpClientInstance.build()
             retrofit = Retrofit.Builder()
                 .baseUrl(BuildConfig.BASE_POKEMON_URL)
                 .addConverterFactory(GsonConverterFactory.create(builder))
                 .client(client)
                 .build()
         }
         return retrofit
     }
 }
