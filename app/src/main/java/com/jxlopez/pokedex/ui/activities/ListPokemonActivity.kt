package com.jxlopez.pokedex.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.jxlopez.pokedex.R
import com.jxlopez.pokedex.common.Constant
import kotlinx.android.synthetic.main.activity_list_pokemon.*

class ListPokemonActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pokemon)

        listPokemonsRecyclerView.setHasFixedSize(true)
        listPokemonsRecyclerView.layoutManager = GridLayoutManager(this@ListPokemonActivity, Constant.COUNT_SPAN)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_pokemon, menu)
        var searchItem = menu?.findItem(R.id.actionSearch)
        var searchView = searchItem?.actionView as SearchView

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
