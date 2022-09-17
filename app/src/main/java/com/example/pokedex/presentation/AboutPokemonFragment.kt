package com.example.pokedex.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColor
import androidx.core.view.isVisible
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentAboutPokemonBinding
import com.example.pokedex.util.PokemonColorUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutPokemonFragment : Fragment() {


    private var _binding: FragmentAboutPokemonBinding? = null
    private val binding get() = _binding!!

    private val viewModel:PokemonViewModel by hiltNavGraphViewModels(R.id.main_nav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAboutPokemonBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString("id"))
        viewModel.getPokemonById(id).observe(viewLifecycleOwner){ pokemon ->
            binding?.apply {
                pokemonName.text = pokemon.name
                pokemonId.text = pokemon.id

                val color = PokemonColorUtil(view.context).getPokemonColor(pokemon.typeofpokemon)
                mainLayout.setBackgroundColor(color)
                progressHp.setIndicatorColor(color)
                progressAtk.setIndicatorColor(color)
                progressDef.setIndicatorColor(color)
                progressSatk.setIndicatorColor(color)
                progressSdef.setIndicatorColor(color)
                progressSpd.setIndicatorColor(color)
                tvAbout.setTextColor(color)
                textStats.setTextColor(color)
                tvHp.setTextColor(color)
                tvAtk.setTextColor(color)
                tvDef.setTextColor(color)
                tvSatk.setTextColor(color)
                tvSdef.setTextColor(color)
                tvSpd.setTextColor(color)

                imagePokemon.let {
                    Glide.with(binding.root)
                        .load(pokemon.imageurl)
                        .into(it)
                }

                pokemon.typeofpokemon?.getOrNull(0).let { firstType ->
                    textType1.text = firstType
                    textType1.isVisible = firstType != null
                    typePokemon1.isVisible = firstType != null

                }

                pokemon.typeofpokemon?.getOrNull(1).let { secondType ->
                    textType2.text = secondType
                    textType1.isVisible = secondType != null
                    typePokemon2.isVisible = secondType != null
                }

                textWeight.text = pokemon.weight
                textHeight.text = pokemon.height


                pokemon.abilities?.getOrNull(0).let { secondMove ->
                    textMove2.text = secondMove
                    textMove2.isVisible = secondMove != null
                }

                textDescription.text = pokemon.xdescription

                hpStats.text = pokemon.hp.toString()
                atkStats.text = pokemon.attack.toString()
                defStats.text = pokemon.defense.toString()
                satkStats.text = pokemon.special_attack.toString()
                sdefStats.text = pokemon.special_defense.toString()
                spdStats.text = pokemon.speed.toString()

                progressHp.setProgress(pokemon.hp!!)
                progressAtk.setProgress(pokemon.attack!!)
                progressDef.setProgress(pokemon.defense!!)
                progressSatk.setProgress(pokemon.special_attack!!)
                progressSdef.setProgress(pokemon.special_defense!!)
                progressSpd.setProgress(pokemon.speed!!)


            }

        }
    }

}