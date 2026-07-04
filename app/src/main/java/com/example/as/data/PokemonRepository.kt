package com.example.`as`.data

import com.example.`as`.model.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepository {

    private val api: PokemonApi = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonApi::class.java)

    suspend fun listarPokemons(): List<Pokemon> {
        val resposta = api.listarPokemons()

        return resposta.results.map { resultado ->

            val id = resultado.url.dropLast(1).substringAfterLast("/").toInt()

            val urlImagem = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

            Pokemon(
                id = id,
                nome = resultado.name.replaceFirstChar { it.uppercase() },
                imagemUrl = urlImagem,
                tipo = "Toque para ver"
            )
        }
    }

    suspend fun buscarPokemon(id: Int): Pokemon {
        return api.buscarPokemon(id).toPokemon()
    }
}