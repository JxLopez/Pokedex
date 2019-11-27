package com.jxlopez.pokedex.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jxlopez.pokedex.data.network.APIService
import com.jxlopez.pokedex.data.network.Response.PokemonResponse
import com.jxlopez.pokedex.data.network.Response.PokemonResponseOnComplete
import com.jxlopez.pokedex.data.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository {
    private var apiService: APIService? = RetrofitHelper.getAPIService()
    val data = MutableLiveData<PokemonResponseOnComplete>()

    fun getPokemons(limit: Int, offset: Int): LiveData<PokemonResponseOnComplete> {
        val responseComplete = PokemonResponseOnComplete()
        apiService?.getPokemons(limit, offset)?.enqueue(object :
            Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if(response.code() == 200) {
                    responseComplete.objeto = response.body()
                }
                responseComplete.statusCode = response.code()
                data.postValue(responseComplete)
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                responseComplete.throwable = t
                data.postValue(responseComplete)
            }
        })
        return data
    }
}