package com.jxlopez.pokedex.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jxlopez.pokedex.data.network.Response.PokemonResponseOnComplete
import com.jxlopez.pokedex.repository.PokemonRepository

class PokemonViewModel : AndroidViewModel {
    var pokemonRepository = PokemonRepository()
    constructor(application: Application) : super(application)

    fun getPokemons(limit: Int, offset: Int): LiveData<PokemonResponseOnComplete> {
        return pokemonRepository.getPokemons(limit, offset)
    }
}