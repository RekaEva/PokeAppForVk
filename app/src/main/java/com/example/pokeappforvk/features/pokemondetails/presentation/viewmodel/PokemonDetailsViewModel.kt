package com.example.pokeappforvk.features.pokemondetails.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pokeappforvk.features.pokemondetails.domain.usecase.GetPokemonDetails
import com.example.pokeappforvk.features.pokemonlist.domain.usecase.GetPokemonList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonDetailsViewModel @Inject constructor(
    private val pokemonDetails: GetPokemonDetails
):ViewModel() {

    private val _uiState = MutableStateFlow(PokemonUiState())
    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()

    fun getPokemonDetails(name : String){
        viewModelScope.launch {
            try {
                _uiState.emit(_uiState.value.copy(isLoading = true))
                val data = pokemonDetails.getPokemonDetails(name)
                _uiState.update { currentState ->
                    currentState.copy(
                        pokemonDetails = data,
                        isLoading = false
                    )
                }
            }catch (errorMessage : Exception){
                _uiState.update { currentState ->
                    currentState.copy(
                        error = errorMessage,
                        isLoading = false
                    )
                }
            }
        }
    }
}