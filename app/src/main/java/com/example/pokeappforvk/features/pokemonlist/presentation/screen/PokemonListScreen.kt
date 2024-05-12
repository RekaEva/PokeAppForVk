package com.example.pokeappforvk.features.pokemonlist.presentation.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.pokeappforvk.R
import com.example.pokeappforvk.features.pokemonlist.presentation.viewmodel.PokemonsListViewModel
import java.net.ConnectException
import java.net.UnknownHostException


@Composable
fun PokemonListScreen(
    pokemonsListViewModel: PokemonsListViewModel,
    onClickNav: (String) -> Unit
) {
    val data = pokemonsListViewModel.getPokemonList().collectAsLazyPagingItems()

    Scaffold() { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (data.loadState.refresh) {
                is LoadState.Loading -> {
                    Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                )
                {
                    CircularProgressIndicator()
                }
                }
                is LoadState.Error -> {
                    val errorState = data.loadState.refresh as LoadState.Error
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(bottom = 10.dp)
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            text = errorMessage(errorState.error),
                            textAlign = TextAlign.Center
                        )
                        OutlinedButton(
                            onClick = { data.retry() },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 20.dp)
                        ) {
                            Text(
                                text = "Update",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                is LoadState.NotLoading -> {
                    LazyColumn {
                        items(data) { pokemon ->
                            Card(modifier = Modifier.clickable {
                                if (pokemon != null) {
                                    Log.d("Qwer", "List Screen${pokemon.name}")
                                    onClickNav(pokemon.name)
                                }
                            }) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    if (pokemon != null) {
                                        Column(Modifier.padding(8.dp)) {
                                            if (pokemon.name != null) {
                                                Text(text = pokemon.name)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        when (data.loadState.append){
                            is LoadState.Error ->{
                                item {
                                    Column(modifier = Modifier
                                        .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center)
                                    {
                                        Text(
                                            text = "Error : New page is not available",
                                            color = MaterialTheme.colorScheme.error
                                        )
                                        IconButton(
                                            onClick = { data.retry() }
                                        ) {
                                            Icon(Icons.Filled.Refresh,
                                                contentDescription = "reload list"
                                            )
                                        }
                                    }
                                }
                            }
                            LoadState.Loading -> {
                                item {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentWidth()
                                    )
                                }
                            }
                            is LoadState.NotLoading -> {}
                        }

                    }

                }
            }
        }
    }
}


@Composable
fun errorMessage(errorMessage: Throwable): String {
    return when (errorMessage) {
        is ConnectException ->
            "Bad Internet Connection! (testmessage)"
//            stringResource(R.string.internet_connection_error)// Вот тут добавить стринги!!!
        else -> errorMessage.toString()
    }
}


@Composable
fun CustomText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}


