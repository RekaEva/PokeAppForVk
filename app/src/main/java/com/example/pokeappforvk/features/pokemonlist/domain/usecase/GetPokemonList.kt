package com.example.pokeappforvk.features.pokemonlist.domain.usecase

import androidx.paging.PagingData
import com.example.pokeappforvk.domain.PokemonRepository
import com.example.pokeappforvk.features.pokemonlist.domain.models.PokemonListResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonList @Inject constructor(
    private val repository: PokemonRepository
)
{

    fun getPokemonList() : Flow<PagingData<PokemonListResult>> {
        return repository.getPokemonList()
    }

//    suspend fun getPokemonList() : Pokemons {
//        return repository.getPokemonList()
//    }
}