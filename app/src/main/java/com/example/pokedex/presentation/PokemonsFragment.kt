package com.example.pokedex.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonsBinding
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PokemonsFragment : Fragment(), PokemonAdapter.PokemonItemListener {

    private var _binding : FragmentPokemonsBinding? = null
    private val binding get() = _binding!!

    private val viewModel:PokemonViewModel by hiltNavGraphViewModels(R.id.main_nav)

    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPokemonsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = PokemonAdapter(this)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),3)
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.pokemons.observe(viewLifecycleOwner){
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    if(!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                Resource.Status.LOADING ->
                    Timber.e("pokemons loading")
            }
        }
    }

    override fun onClickedPokemon(pokemon: Int) {
        val action = PokemonsFragmentDirections.actionPokemonsFragmentToViewPagerPokemonsFragment(pokemon)
        this.findNavController().navigate(action)
    }
}