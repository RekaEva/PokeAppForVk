package com.example.pokeappforvk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokeappforvk.ScreensName.Companion.POKEMONS_LIST_SCREEN
import com.example.pokeappforvk.di.DaggerApplicationComponent
import com.example.pokeappforvk.features.pokemonlist.presentation.screen.PokemonListScreen
import com.example.pokeappforvk.features.pokemonlist.presentation.viewmodel.PokemonsListViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var pokemonsListViewModel: PokemonsListViewModel
    private val component = DaggerApplicationComponent.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonListScreen(pokemonsListViewModel = pokemonsListViewModel)
//            val navController = rememberNavController()
//            NavHost(
//                navController = navController,
//                startDestination = POKEMONS_LIST_SCREEN
//            ) {
//                composable(POKEMONS_LIST_SCREEN) {
//                    PokemonListScreen(pokemonsListViewModel = pokemonsListViewModel)
//                }
//            }
        }
    }
}

class ScreensName {
    companion object {
        const val POKEMONS_LIST_SCREEN = "pokemonsListScreen"
    }
}