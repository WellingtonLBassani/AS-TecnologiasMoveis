package com.example.`as`.data

import com.example.`as`.model.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepository {

    private val api: PokemonApi = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/") // Nova Base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonApi::class.java)

    suspend fun listarPokemons(): List<Pokemon> {
        val resposta = api.listarPokemons()

        return resposta.results.map { resultado ->
            // Truque: A URL é "https://pokeapi.co/api/v2/pokemon/1/".
            // Vamos cortar as barras e pegar o número 1!
            val id = resultado.url.dropLast(1).substringAfterLast("/").toInt()

            // Montamos o link da imagem sem precisar fazer outra requisição
            val urlImagem = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

            Pokemon(
                id = id,
                nome = resultado.name.replaceFirstChar { it.uppercase() },
                imagemUrl = urlImagem,
                tipo = "Toque para ver" // Na lista não temos o tipo, só no detalhe!
            )
        }
    }

    suspend fun buscarPokemon(id: Int): Pokemon {
        // Aqui usamos o Mapper (toPokemon) que criamos no Passo 3
        return api.buscarPokemon(id).toPokemon()
    }
}