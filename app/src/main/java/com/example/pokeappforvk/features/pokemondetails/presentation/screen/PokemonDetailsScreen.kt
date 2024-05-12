package com.example.pokeappforvk.features.pokemondetails.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.pokeappforvk.features.pokemondetails.presentation.viewmodel.PokemonDetailsViewModel
import com.example.pokeappforvk.features.pokemonlist.presentation.viewmodel.PokemonsListViewModel

@Composable
fun PokemonDetailsScreen(
    pokemonDetailsViewModel: PokemonDetailsViewModel,
    name : String?,
){
    val uiState by pokemonDetailsViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        if (name != null) {
            pokemonDetailsViewModel.getPokemonDetails(name)
        }
    }
     if (name != null){
         Column {
             Text(text = "Hi, i am $name")
             Text(text = uiState.pokemonDetails?.weight.toString())
         }
     }
    else{
        Text(text = "Error : name is null")
     }
}