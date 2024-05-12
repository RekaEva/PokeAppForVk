package com.example.pokeappforvk

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokeappforvk.ScreensName.Companion.POKEMONS_DETAILS_SCREEN
import com.example.pokeappforvk.ScreensName.Companion.POKEMONS_LIST_SCREEN
import com.example.pokeappforvk.di.DaggerApplicationComponent
import com.example.pokeappforvk.features.pokemondetails.presentation.screen.PokemonDetailsScreen
import com.example.pokeappforvk.features.pokemondetails.presentation.viewmodel.PokemonDetailsViewModel
import com.example.pokeappforvk.features.pokemonlist.presentation.screen.PokemonListScreen
//import com.example.pokeappforvk.features.pokemonlist.presentation.screen.PokemonListScreen
import com.example.pokeappforvk.features.pokemonlist.presentation.viewmodel.PokemonsListViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var pokemonsListViewModel: PokemonsListViewModel
    @Inject
    lateinit var pokemonDetailsViewModel: PokemonDetailsViewModel

    private val component = DaggerApplicationComponent.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = POKEMONS_LIST_SCREEN
            ) {
                composable(POKEMONS_LIST_SCREEN) {
                    PokemonListScreen(pokemonsListViewModel = pokemonsListViewModel){
                        navController.navigate("$POKEMONS_DETAILS_SCREEN/$it")
                    }
                }
                composable("$POKEMONS_DETAILS_SCREEN/{name}"){name ->
                    Log.d("Qwer", "composable before$name}")
                    val pokemonName = name.arguments?.getString("name")
                    Log.d("Qwer", "composable after $pokemonName}")
                    PokemonDetailsScreen(pokemonDetailsViewModel = pokemonDetailsViewModel,
                        name = pokemonName)
                }
            }
        }
    }
}

class ScreensName {
    companion object {
        const val POKEMONS_LIST_SCREEN = "pokemonsListScreen"
        const val POKEMONS_DETAILS_SCREEN = "pokemonsDetailsScreen"
    }
}



// Ошибка: Спосок при отключение и подключении интернета не продолжает подгружаться - fix
//