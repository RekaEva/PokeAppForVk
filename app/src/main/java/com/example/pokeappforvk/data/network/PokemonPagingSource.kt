package com.example.pokeappforvk.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokeappforvk.features.pokemonlist.domain.models.PokemonListResult

class PokemonPagingSource(
    private val loader: PokemonApi
) : PagingSource<Int, PokemonListResult>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonListResult>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListResult> {
        val key = params.key ?: 0
        val offset = params.loadSize * key
        return try {
            val response = loader.getPokemonList(limit = params.loadSize, offset = offset)
            val pokelist = response.pokelist
            val nextKey = if (pokelist.size < params.loadSize) null else key + 1
            LoadResult.Page(
                data = pokelist,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
