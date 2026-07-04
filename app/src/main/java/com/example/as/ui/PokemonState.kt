package com.example.`as`.ui

import com.example.`as`.model.Pokemon
import kotlinx.serialization.Serializable

data class PokemonUiState(
    val pokemons: List<Pokemon> = emptyList(),
    val pokemonDetalhe: Pokemon? = null,
    val carregando: Boolean = false,
    val erro: String? = null
)

@Serializable
object ListaPokemons

@Serializable
data class DetalhePokemon(val id: Int)