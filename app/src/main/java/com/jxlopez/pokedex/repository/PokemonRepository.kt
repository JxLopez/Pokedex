package com.jxlopez.pokedex.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jxlopez.pokedex.data.database.PokemonDatabase
import com.jxlopez.pokedex.data.database.dao.PokemonDao
import com.jxlopez.pokedex.data.database.entity.Pokemon
import com.jxlopez.pokedex.data.network.APIService
import com.jxlopez.pokedex.data.network.Response.PokemonResponse
import com.jxlopez.pokedex.data.network.Response.PokemonResponseOnComplete
import com.jxlopez.pokedex.data.network.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PokemonRepository(application: Application) {
    private var apiService: APIService? = RetrofitHelper.getAPIService()
    private var pokemonDao: PokemonDao? = null
    val data = MutableLiveData<List<Pokemon>>()

    init {
        val pokemonDatabase = PokemonDatabase.getDatabase(application)
        pokemonDao = pokemonDatabase.pokemonDao()
    }

    suspend fun getPokemons(limit: Int, offset: Int, data: MutableLiveData<List<Pokemon>>) =
        suspendCoroutine<MutableLiveData<List<Pokemon>>> { continuation ->
            val responseComplete = PokemonResponseOnComplete()
            apiService?.getPokemons(limit, offset)?.enqueue(object :
                Callback<PokemonResponse> {
                override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                    val list : MutableList<Pokemon> = ArrayList()
                    if(response.code() == 200) {
                        responseComplete.objeto = response.body()
                        responseComplete.objeto?.results?.forEach {
                            val pokemon = Pokemon(it.id, it.name, it.url)
                            list.add(pokemon)
                        }
                        GlobalScope.launch {
                            pokemonDao?.savePokemons(list)
                        }
                        data.postValue(list)
                    } else {
                        GlobalScope.launch {
                            data.postValue(pokemonDao?.getPokemon(limit, offset))
                        }
                    }
                    continuation.resume(data)

                }

                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    GlobalScope.launch {
                        data.postValue(pokemonDao?.getPokemon(limit, offset))
                    }
                    continuation.resume(data)
                }
            })
        }
}