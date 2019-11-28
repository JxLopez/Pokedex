package com.jxlopez.pokedex.ui.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jxlopez.pokedex.R
import com.jxlopez.pokedex.common.Constant
import com.jxlopez.pokedex.data.PreferencesHelper
import com.jxlopez.pokedex.data.database.entity.Pokemon
import com.jxlopez.pokedex.ui.adapter.PokemonAdapter
import com.jxlopez.pokedex.viewmodel.PokemonViewModel
import kotlinx.android.synthetic.main.activity_list_pokemon.*

class ListPokemonActivity : BaseActivity() {

    private var pokemonViewModel: PokemonViewModel? = null
    private var pokemonAdapter: PokemonAdapter? = null
    private var doneLoad = true
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pokemon)
        initComponents()
        loadData()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.actionLogOut -> {
                generateMessageDialog(
                    getString(R.string.logout),
                    getString(R.string.confirm_log_out),
                    getString(android.R.string.yes),
                    getString(android.R.string.cancel),
                    false,
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        logOut()
                        dialogInterface.dismiss()
                    }, DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                )
                true
            } else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }

    private fun initComponents() {
        listPokemonsRecyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this@ListPokemonActivity, Constant.COUNT_SPAN)
        listPokemonsRecyclerView.layoutManager = layoutManager
        setRecyclerViewScrollListener(layoutManager)
        pokemonAdapter = PokemonAdapter(this@ListPokemonActivity, ArrayList<Pokemon>())
        listPokemonsRecyclerView.adapter = pokemonAdapter

        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)

        pokemonViewModel?.data?.observe(this, Observer {
            it?.let { data ->
                pokemonAdapter?.addData(data)
            }
            doneLoad = true
        })
    }

    private fun setRecyclerViewScrollListener(layoutManager: GridLayoutManager) {
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItemCount = layoutManager.findFirstVisibleItemPosition()

                    if(doneLoad && ((visibleItemCount + pastVisibleItemCount) >= totalItemCount)) {
                        doneLoad = false
                        offset = offset.plus(20)
                        loadData()
                    }
                }
            }
        }
        listPokemonsRecyclerView.addOnScrollListener(scrollListener)
    }

    private fun loadData() {
        pokemonViewModel?.refreshData(20, offset)
    }

    private fun logOut() {
        PreferencesHelper.setLogged(this@ListPokemonActivity, false)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}
