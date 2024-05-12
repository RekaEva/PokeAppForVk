package com.example.pokeappforvk.features.pokemondetails.domain.usecase

import com.example.pokeappforvk.domain.PokemonRepository
import com.example.pokeappforvk.features.pokemondetails.domain.models.PokemonDetails
import javax.inject.Inject

class GetPokemonDetails @Inject constructor(private val repository: PokemonRepository) {
    suspend fun getPokemonDetails(name : String) : PokemonDetails {
        return repository.getPokemonDetails(name)
    }
}

