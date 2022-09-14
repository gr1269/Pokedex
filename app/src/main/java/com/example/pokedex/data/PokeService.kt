package com.example.pokedex.data

import com.example.pokedex.domain.Pokemon
import retrofit2.Response
import retrofit2.http.GET

interface PokeService {
    @GET("pokemon.json")
    suspend fun get(): Response<List<Pokemon>>
}