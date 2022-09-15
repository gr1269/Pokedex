package com.example.pokedex.util

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.pokedex.R
import java.util.*

class PokemonColorUtil(var context: Context) {

    @ColorInt
    fun getPokemonColor(typeOfPokemon: List<String>?): Int {
        val type = typeOfPokemon?.getOrNull(0)
        val color = when(type?.lowercase(Locale.ROOT)) {
            "rock" -> R.color.rock
            "ghost" -> R.color.ghost
            "steel" -> R.color.steel
            "water" -> R.color.water
            "grass" -> R.color.grass
            "psychic" -> R.color.psychic
            "ice" -> R.color.ice
            "dark" -> R.color.dark
            "fairy" -> R.color.fairy
            "normal" -> R.color.normal
            "fighting" -> R.color.fighting
            "flying" -> R.color.flying
            "poison" -> R.color.poison
            "ground" -> R.color.ground
            "bug" -> R.color.bug
            "fire" -> R.color.fire
            "electric" -> R.color.electric
            "dragon" -> R.color.dragon
            else -> R.color.normal
        }
        return convertColor(color)
    }

    @ColorInt
    fun convertColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }
}