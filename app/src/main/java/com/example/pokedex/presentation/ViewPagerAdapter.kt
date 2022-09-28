package com.example.pokedex.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentAboutPokemonBinding
import com.example.pokedex.databinding.ItemPokemonBinding
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.util.PokemonColorUtil

class ViewPagerAdapter():RecyclerView.Adapter<AboutPokemonViewHolder>() {

    private val items = ArrayList<Pokemon>()

    fun setItems(items: ArrayList<Pokemon>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutPokemonViewHolder {
        val binding: FragmentAboutPokemonBinding =
            FragmentAboutPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_about_pokemon,parent,false)
        return AboutPokemonViewHolder(binding,view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AboutPokemonViewHolder, position: Int) =
        holder.bind(items[position])

}

class AboutPokemonViewHolder(private val itemBinding: FragmentAboutPokemonBinding,
                             private val view: View) : RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var pokemon: Pokemon


    fun bind(item: Pokemon) {
        this.pokemon = item

        itemBinding?.apply {
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
                Glide.with(itemBinding.root)
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