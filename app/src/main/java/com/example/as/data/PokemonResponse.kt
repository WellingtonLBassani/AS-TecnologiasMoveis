package com.example.`as`.data

import com.example.`as`.model.Pokemon
import com.example.`as`.model.PokemonResponse

fun PokemonResponse.toPokemon() = Pokemon(
    id = id,
    nome = name.replaceFirstChar { it.uppercase() }, // Deixa o nome bonito (ex: Bulbasaur)
    imagemUrl = sprites.frontDefault,
    tipo = types.first().type.name // Pega o primeiro tipo do Pokémon
)