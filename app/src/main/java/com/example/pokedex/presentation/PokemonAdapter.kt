package com.example.pokedex.presentation

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.pokedex.R
import com.example.pokedex.databinding.ItemPokemonBinding
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.util.PokemonColorUtil

class PokemonAdapter(private val listener: PokemonItemListener):RecyclerView.Adapter<PokemonViewHolder>() {

    interface PokemonItemListener {
        fun onClickedPokemon(pokemonId: Int)
    }

    private val items = ArrayList<Pokemon>()

    fun setItems(items: ArrayList<Pokemon>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding: ItemPokemonBinding =
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon,parent,false)
        return PokemonViewHolder(binding,view, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(items[position])
    }

}

    class PokemonViewHolder(private val itemBinding: ItemPokemonBinding,
                            private val view: View,
                            private val listener: PokemonAdapter.PokemonItemListener) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var pokemon: Pokemon

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: Pokemon) {
            this.pokemon = item

            val color = PokemonColorUtil(view.context).getPokemonColor(item.typeofpokemon)
            itemBinding.layoutColor.setBackgroundColor(color)
            itemBinding.pokemonId.setTextColor(color)
            itemBinding.pokemonName.text = item.name
            itemBinding.pokemonId.text = item.id

            Glide.with(itemBinding.root)
                .load(item.imageurl)
                .into(itemBinding.pokemonImage)
        }

        override fun onClick(v: View?) {
            var position = adapterPosition
            listener.onClickedPokemon(position)
        }
    }
