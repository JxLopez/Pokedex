package com.jxlopez.pokedex.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jxlopez.pokedex.R
import com.jxlopez.pokedex.common.Constant
import com.jxlopez.pokedex.viewmodel.PokemonViewModel
import kotlinx.android.synthetic.main.activity_list_pokemon.*

class ListPokemonActivity : BaseActivity() {

    private var pokemonViewModel: PokemonViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pokemon)

        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)

        pokemonViewModel?.getPokemons(20,20)?.observe(this, Observer {
            when {
                it.statusCode == 200 -> {
                    it.objeto?.results?.forEach {
                        Log.e("forEache","${it.name}")
                    }
                }
                else -> Toast.makeText(this@ListPokemonActivity, getString(R.string.login_error_server), Toast.LENGTH_SHORT).show()
            }
        })

        listPokemonsRecyclerView.setHasFixedSize(true)
        listPokemonsRecyclerView.layoutManager = GridLayoutManager(this@ListPokemonActivity, Constant.COUNT_SPAN)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_pokemon, menu)
        val searchItem = menu?.findItem(R.id.actionSearch)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.e("onQueryTextSubmit",query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.e("onQueryTextChange",newText)
                return false
            }
        })

        return true
    }
}
