package com.example.pokeappforvk.features.pokemonlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pokeappforvk.features.pokemonlist.domain.usecase.GetPokemonList
import javax.inject.Inject

class PokemonsListViewModel @Inject constructor (
    private val pokemonList: GetPokemonList
): ViewModel(){
    fun getPokemonList() = pokemonList.getPokemonList()
}