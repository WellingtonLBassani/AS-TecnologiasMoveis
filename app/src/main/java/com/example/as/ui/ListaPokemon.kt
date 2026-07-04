package com.example.`as`.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPokemonsScreen(
    uiState: PokemonUiState,
    onPokemonClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pokédex") })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                uiState.carregando -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                uiState.erro != null -> Text(
                    text = uiState.erro,
                    modifier = Modifier.align(Alignment.Center)
                )
                else -> LazyColumn {
                    items(uiState.pokemons, key = { it.id }) { pokemon ->
                        PokemonCard(
                            pokemon = pokemon,
                            onClick = { onPokemonClick(pokemon.id) }
                        )
                    }
                }
            }
        }
    }
}