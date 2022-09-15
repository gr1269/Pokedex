package com.example.pokedex.data.remote

import com.example.pokedex.util.BaseDataSource
import javax.inject.Inject

class PokeRemoteDataSource @Inject constructor(
    private val service: PokeService
): BaseDataSource() {
    suspend fun getPokemons() = getResult { service.get() }
}