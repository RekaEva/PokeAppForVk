package com.example.pokeappforvk.features.pokemondetails.domain.models

data class PokemonDetails(
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val weight: Int,
)