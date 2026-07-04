package com.example.`as`.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhePokemonScreen(
    id: Int,
    uiState: PokemonUiState,
    onCarregarDetalhe: (Int) -> Unit,
    onVoltar: () -> Unit
) {
    LaunchedEffect(id) {
        onCarregarDetalhe(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhe do Pokémon") },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.carregando -> CircularProgressIndicator()
                uiState.erro != null -> Text(uiState.erro)
                uiState.pokemonDetalhe != null -> {
                    val p = uiState.pokemonDetalhe
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(24.dp)
                    ) {
                        AsyncImage(
                            model = p.imagemUrl,
                            contentDescription = p.nome,
                            modifier = Modifier
                                .size(160.dp)
                                .clip(CircleShape)
                        )
                        Text(text = p.nome, style = MaterialTheme.typography.headlineLarge)
                        Text(text = "Número: #${p.id}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Tipo Principal: ${p.tipo.replaceFirstChar { it.uppercase() }}")
                    }
                }
            }
        }
    }
}