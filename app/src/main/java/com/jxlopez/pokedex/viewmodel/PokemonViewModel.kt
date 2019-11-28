package com.jxlopez.pokedex.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jxlopez.pokedex.data.database.entity.Pokemon
import com.jxlopez.pokedex.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel(application: Application) : AndroidViewModel(application) {
    var pokemonRepository = PokemonRepository(application)
    val data = MutableLiveData<List<Pokemon>>()

    fun refreshData(limit: Int, offset: Int) {
        viewModelScope.launch {
            pokemonRepository.getPokemons(limit, offset, data)
        }

    }

}