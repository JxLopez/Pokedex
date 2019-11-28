package com.jxlopez.pokedex.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jxlopez.pokedex.common.Constant

@Entity(tableName = Constant.Database.TablePokemon.NAME_TABLE)
class Pokemon(
    @PrimaryKey
    @ColumnInfo(name = Constant.Database.TablePokemon.ID)
    val id: Int,
    @ColumnInfo(name = Constant.Database.TablePokemon.NAME)
    val name: String,
    @ColumnInfo(name = Constant.Database.TablePokemon.URL)
    val url: String
)
