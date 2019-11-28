package com.jxlopez.pokedex

import com.jxlopez.pokedex.data.database.entity.Pokemon
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class EntityRoomUnitTest {
    @Test
    fun testId(){
        val pokemon = Pokemon(1, "Pokemon", "https://pokeapi.co/api/v2/pokemon/181/")
        assertEquals(pokemon.id, 1)
    }

    @Test
    fun testName(){
        val pokemon = Pokemon(1, "Pokemon", "https://pokeapi.co/api/v2/pokemon/181/")
        assertEquals(pokemon.name, "Pokemon")
    }

    @Test
    fun testUrl(){
        val pokemon = Pokemon(1, "Pokemon", "https://pokeapi.co/api/v2/pokemon/181/")
        assertEquals(pokemon.url,"https://pokeapi.co/api/v2/pokemon/181/" )
    }
}