package com.example.pokeappforvk.features.pokemondetails.presentation.viewmodel

import com.example.pokeappforvk.features.pokemondetails.domain.models.PokemonDetails

data class PokemonUiState (
    val pokemonDetails: PokemonDetails? = null,
    val error: Exception? = null,
    val isLoading: Boolean = false
)