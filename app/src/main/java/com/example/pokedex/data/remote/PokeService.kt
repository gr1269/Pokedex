package com.example.pokedex.data.remote

import com.example.pokedex.domain.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface PokeService {
    @GET("pokemon.json")
    suspend fun get(): Response<List<Pokemon>>
}