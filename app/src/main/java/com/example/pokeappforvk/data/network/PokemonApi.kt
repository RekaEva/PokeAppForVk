package com.example.pokeappforvk.data.network

import com.example.pokeappforvk.features.pokemondetails.domain.models.PokemonDetails
import com.example.pokeappforvk.features.pokemonlist.domain.models.PokemonListX
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  PokemonApi {


    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokemonListX

    @GET("pokemon/{name}")
    suspend fun getPokemonDetailsByName(@Path("name") name: String): PokemonDetails
}