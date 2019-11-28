package com.jxlopez.pokedex.data.network

object RetrofitHelper {
    fun getAPIService(): APIService {
        return RetrofitClient.getClient()!!.create(APIService::class.java)
    }
}