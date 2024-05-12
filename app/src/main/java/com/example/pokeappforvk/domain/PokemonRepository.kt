package com.example.pokeappforvk.domain

import androidx.paging.PagingData
import com.example.pokeappforvk.features.pokemondetails.domain.models.PokemonDetails
import com.example.pokeappforvk.features.pokemonlist.domain.models.PokemonListResult
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList() : Flow<PagingData<PokemonListResult>>
    suspend fun getPokemonDetails(name: String) : PokemonDetails
}
