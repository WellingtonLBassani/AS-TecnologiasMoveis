package com.example.`as`.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
// Atenção: Ajuste os imports abaixo para o nome do pacote do seu projeto
// import br.com.seunome.seuprojeto.ui.ListaPokemons
// import br.com.seunome.seuprojeto.ui.DetalhePokemon

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val viewModel: PokemonViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = ListaPokemons) {
        composable<ListaPokemons> {
            LaunchedEffect(Unit) {
                viewModel.carregarPokemons()
            }
            ListaPokemonsScreen(
                uiState = uiState,
                onPokemonClick = { id -> navController.navigate(DetalhePokemon(id)) }
            )
        }
        composable<DetalhePokemon> { backStackEntry ->
            val rota = backStackEntry.toRoute<DetalhePokemon>()
            DetalhePokemonScreen(
                id = rota.id,
                uiState = uiState,
                onCarregarDetalhe = { viewModel.carregarDetalhe(it) },
                onVoltar = { navController.popBackStack() }
            )
        }
    }
}