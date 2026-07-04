package com.example.`as`.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.`as`.ui.theme.PokedexDark
import com.example.`as`.ui.theme.PokedexRed

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
            Surface(
                color = PokedexRed,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 12.dp)
                        .statusBarsPadding(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onVoltar) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Detalhes do Pokémon",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
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
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.carregando -> CircularProgressIndicator(color = PokedexRed)
                uiState.erro != null -> Text(uiState.erro, color = Color.White)
                uiState.pokemonDetalhe != null -> {
                    val p = uiState.pokemonDetalhe
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(24.dp)
                    ) {
                        AsyncImage(
                            model = p.imagemUrl,
                            contentDescription = p.nome,
                            modifier = Modifier.size(200.dp)
                        )

                        Text(
                            text = p.nome,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Número: #${p.id.toString().padStart(3, '0')}",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.LightGray
                        )

                        val corTipo = obterCorDoTipo(p.tipo)
                        Surface(
                            color = corTipo.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, corTipo)
                        ) {
                            Text(
                                text = p.tipo.uppercase(),
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                                color = corTipo,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}