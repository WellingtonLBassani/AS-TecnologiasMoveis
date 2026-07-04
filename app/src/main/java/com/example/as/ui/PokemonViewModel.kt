package com.example.`as`.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.`as`.data.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private val _uiState = MutableStateFlow(PokemonUiState())
    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()

    fun carregarPokemons() {
        viewModelScope.launch {
            _uiState.update { it.copy(carregando = true, erro = null) }
            try {
                val lista = repository.listarPokemons()
                _uiState.update { it.copy(pokemons = lista, carregando = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(erro = "Erro ao carregar Pokémons", carregando = false) }
            }
        }
    }

    fun carregarDetalhe(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(carregando = true, erro = null) }
            try {
                val pokemon = repository.buscarPokemon(id)
                _uiState.update { it.copy(pokemonDetalhe = pokemon, carregando = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(erro = "Erro ao carregar detalhe", carregando = false) }
            }
        }
    }
}