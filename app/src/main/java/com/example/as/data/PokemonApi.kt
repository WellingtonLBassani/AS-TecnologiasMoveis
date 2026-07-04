package com.example.`as`.data

import com.example.`as`.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

data class PokemonListResponse(
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

interface PokemonApi {
    @GET("pokemon?limit=151")
    suspend fun listarPokemons(): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun buscarPokemon(@Path("id") id: Int): PokemonResponse
}