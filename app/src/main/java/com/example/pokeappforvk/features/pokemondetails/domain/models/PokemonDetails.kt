package com.example.pokeappforvk.features.pokemondetails.domain.models

data class PokemonDetails(
    val abilities: List<Ability>,
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int
)