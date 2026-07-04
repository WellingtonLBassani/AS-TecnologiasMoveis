package com.example.`as`.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.`as`.ui.theme.PokedexBlue
import com.example.`as`.ui.theme.PokedexDark
import com.example.`as`.ui.theme.PokedexRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPokemonsScreen(
    uiState: PokemonUiState,
    onPokemonClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            Surface(
                color = PokedexRed,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .statusBarsPadding(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        color = Color.White,
                        border = BorderStroke(2.dp, Color.Black)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Surface(
                                modifier = Modifier.size(30.dp),
                                shape = CircleShape,
                                color = PokedexBlue
                            ) {}
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "POKÉDEX",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        containerColor = PokedexDark
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                uiState.carregando -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = PokedexRed
                    )
                }
                uiState.erro != null -> {
                    Text(
                        text = uiState.erro,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
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
}