package com.example.pokedex.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.pokedex.R
import com.example.pokedex.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ViewPagerPokemonsFragment : Fragment() {

    private val viewModel:PokemonViewModel by hiltNavGraphViewModels(R.id.main_nav)

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager_pokemons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.viewPagerPokemons)
        adapter = ViewPagerAdapter()
        viewPager.adapter = adapter
        var pokemonArgs = ViewPagerPokemonsFragmentArgs.fromBundle(requireArguments()).pokemonArgs


        viewModel.pokemons.observe(viewLifecycleOwner){
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    if(!it.data.isNullOrEmpty()){
                        adapter.setItems(ArrayList(it.data))
                        var resetPosition = pokemonArgs
                        viewPager.setCurrentItem(resetPosition,false)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                Resource.Status.LOADING ->
                    Timber.e("pokemons loading")
            }
        }
    }

}