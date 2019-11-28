package com.jxlopez.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jxlopez.pokedex.common.Constant
import com.jxlopez.pokedex.data.database.entity.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * FROM ${Constant.Database.TablePokemon.NAME_TABLE} ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPokemon(limit: Int, offset: Int): List<Pokemon>

    @Query("SELECT * FROM ${Constant.Database.TablePokemon.NAME_TABLE} WHERE ${Constant.Database.TablePokemon.NAME} like :name ORDER BY id ASC")
    suspend fun findPokemonByName(name: String): List<Pokemon>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemon: Pokemon)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun savePokemons(pokemons: List<Pokemon>)
}