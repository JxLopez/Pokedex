package com.jxlopez.pokedex

import com.jxlopez.pokedex.data.network.RetrofitHelper
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ApiServiceUnitTest {
    @Test
    fun getPokemonsApi() {
        val apiService = RetrofitHelper.getAPIService()
        val response = apiService.getPokemons(20,0).execute()
        assertEquals(response.code(), 200)
    }
}