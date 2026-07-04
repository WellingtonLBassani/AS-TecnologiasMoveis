package com.example.`as`.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int,
    val nome: String,
    val imagemUrl: String,
    val tipo: String
)

data class PokemonResponse(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<TypeWrapper>
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String
)

data class TypeWrapper(val type: TypeName)
data class TypeName(val name: String)