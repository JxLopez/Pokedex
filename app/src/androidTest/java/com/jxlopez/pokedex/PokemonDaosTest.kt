package com.jxlopez.pokedex

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.jxlopez.pokedex.data.database.PokemonDatabase
import com.jxlopez.pokedex.data.database.entity.Pokemon
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonDaosTest {
    var pokemonDatabase : PokemonDatabase? = null

    @Before
    fun init() {
        pokemonDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, PokemonDatabase::class.java).build()
    }

    @After
    fun finish() {
        pokemonDatabase?.close()
    }

    @Test
    fun getPokemonsTest() {
        val pokemons: MutableList<Pokemon> = ArrayList()
        val pokemon = Pokemon(500, "MiPokemon", "https://pokeapi.co/api/v2/pokemon/181/")
        pokemons.add(pokemon)
        GlobalScope.launch {
            pokemonDatabase?.pokemonDao()?.savePokemons(pokemons)
            val pokemonsList = pokemonDatabase?.pokemonDao()?.getPokemon(10,0)
            assertNotNull(pokemonsList)
        }
    }
}