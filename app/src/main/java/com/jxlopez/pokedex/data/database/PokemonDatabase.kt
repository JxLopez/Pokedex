package com.jxlopez.pokedex.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jxlopez.pokedex.common.Constant
import com.jxlopez.pokedex.data.database.dao.PokemonDao
import com.jxlopez.pokedex.data.database.entity.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase {
            val instanceTemp = INSTANCE
            if(instanceTemp != null) return instanceTemp

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java,
                    Constant.Database.NAME_DB
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}