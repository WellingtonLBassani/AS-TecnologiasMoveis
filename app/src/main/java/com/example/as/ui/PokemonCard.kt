package com.example.`as`.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.`as`.model.Pokemon
import com.example.`as`.ui.theme.PokedexCardGray
import com.example.`as`.ui.theme.PokedexGreen

fun obterCorDoTipo(tipo: String): Color {
    return when (tipo.lowercase()) {
        "grass" -> Color(0xFF51AD60)
        "fire" -> Color(0xFFF08030)
        "water" -> Color(0xFF6890F0)
        "bug" -> Color(0xFFA8B820)
        "normal" -> Color(0xFFA8A878)
        "poison" -> Color(0xFFA040A0)
        "electric" -> Color(0xFFF8D030)
        "ground" -> Color(0xFFE0C068)
        "fighting" -> Color(0xFFC03028)
        "psychic" -> Color(0xFFF85888)
        "rock" -> Color(0xFFB8A038)
        "ice" -> Color(0xFF98D8D8)
        "ghost" -> Color(0xFF705898)
        "dragon" -> Color(0xFF7038F8)
        "dark" -> Color(0xFF705848)
        "steel" -> Color(0xFFB8B8D0)
        "fairy" -> Color(0xFFEE99AC)
        else -> Color.Gray
    }
}

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit
) {
    val corDinamica = obterCorDoTipo(pokemon.tipo)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = PokedexCardGray),
        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier.height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(6.dp)
                    .background(corDinamica)
            )

            AsyncImage(
                model = pokemon.imagemUrl,
                contentDescription = pokemon.nome,
                modifier = Modifier
                    .size(70.dp)
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = pokemon.nome,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )


                Surface(
                    color = corDinamica.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = pokemon.tipo.uppercase(),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        color = corDinamica,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = "#${pokemon.id.toString().padStart(3, '0')}",
                modifier = Modifier.padding(end = 16.dp),
                color = PokedexGreen.copy(alpha = 0.7f),
                style = MaterialTheme.typography.labelLarge,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}