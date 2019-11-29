package com.jxlopez.pokedex.data.network

import com.jxlopez.pokedex.common.Constant
import com.jxlopez.pokedex.data.network.Response.PokemonResponse
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @GET(Constant.EndPoints.GET_POKEMONS)
    fun getPokemons(@Query(Constant.EndPoints.Header.LIMIT) limit: Int,
                    @Query(Constant.EndPoints.Header.OFFSET) offset: Int): Call<PokemonResponse>
}