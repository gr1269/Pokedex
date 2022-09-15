package com.example.pokedex.data

import com.example.pokedex.data.local.PokeDao
import com.example.pokedex.data.remote.PokeRemoteDataSource
import com.example.pokedex.util.performGetOperation
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val remoteDataSource: PokeRemoteDataSource,
    private val localDataSource: PokeDao
) {

    fun getPokemons() = performGetOperation(
        databaseQuery = {localDataSource.getPokemons()},
        networkCall = {remoteDataSource.getPokemons()},
        saveCallResult = {localDataSource.insertAll(it)}
    )
}