package com.example.pokedex.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.domain.Pokemon


@Dao
interface PokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<Pokemon>)

    @Query("SELECT * FROM pokemons")
    fun getPokemons(): LiveData<List<Pokemon>>

    @Query("SELECT * FROM pokemons WHERE id = :id")
    fun getPokemonById(id: Int): LiveData<Pokemon>
}