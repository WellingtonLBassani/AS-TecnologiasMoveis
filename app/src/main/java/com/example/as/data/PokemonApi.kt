package com.example.`as`.data

import com.example.`as`.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

// Resposta da lista principal
data class PokemonListResponse(
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String // Ex: "https://pokeapi.co/api/v2/pokemon/1/"
)

interface PokemonApi {
    // Pegando os 151 primeiros Pokémons
    @GET("pokemon?limit=151")
    suspend fun listarPokemons(): PokemonListResponse

    // Pegando o detalhe de um Pokémon específico
    @GET("pokemon/{id}")
    suspend fun buscarPokemon(@Path("id") id: Int): PokemonResponse
}