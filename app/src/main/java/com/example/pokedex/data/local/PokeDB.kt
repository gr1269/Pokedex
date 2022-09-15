package com.example.pokedex.data.local

import android.content.Context
import androidx.room.*
import com.example.pokedex.domain.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PokemonDB: RoomDatabase() {
     abstract fun pokeDao(): PokeDao

    companion object {
        @Volatile private var instance: PokemonDB? = null
        fun getDB(context: Context): PokemonDB =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it}
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, PokemonDB::class.java, "PokemonDB")
                .fallbackToDestructiveMigration()
                .build()
    }
}

class Converters {
    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toList(string: String): List<String> {
        return string.split(",")
    }
}