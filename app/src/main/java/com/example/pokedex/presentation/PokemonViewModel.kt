package com.example.pokedex.presentation

import androidx.lifecycle.ViewModel
import com.example.pokedex.data.PokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokeRepository): ViewModel() {

    var pokemons = repository.getPokemons()
}