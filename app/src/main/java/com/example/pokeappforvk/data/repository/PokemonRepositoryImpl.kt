package com.example.pokeappforvk.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokeappforvk.data.network.PokemonApi
import com.example.pokeappforvk.data.network.PokemonPagingSource
import com.example.pokeappforvk.domain.PokemonRepository
import com.example.pokeappforvk.features.pokemonlist.domain.models.PokemonListResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi
) : PokemonRepository {

    override fun getPokemonList(): Flow<PagingData<PokemonListResult>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE),
            pagingSourceFactory = { PokemonPagingSource(loader = api) }
        ).flow
    }

//    override fun getPokemonDetails(): PokemonData {
//        TODO("Not yet implemented")
//    }

    companion object{
        const val PAGE_SIZE = 20
    }
}