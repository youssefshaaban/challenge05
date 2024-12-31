package com.banquemisr.challenge05.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.banquemisr.challenge05.ui.movies.MovieListScreen

@Composable
fun NavGraph(modifier: Modifier=Modifier,navController: NavHostController) {
    NavHost(modifier =modifier,
        navController = navController,
        startDestination = NavRoute.Characters.path
    ) {
        addMovieListScreen(navController, this)

        addMovieDetailScreen(navController, this)
    }
}

fun addMovieDetailScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(route = NavRoute.CharacterDetail.withArgsFormat(NavRoute.CharacterDetail.characterId)) {navBackStackEntry->
        val charactersId = navBackStackEntry.arguments?.getString(NavRoute.CharacterDetail.characterId,"")
        /* We check if it's not null */
        charactersId?.let { id->

        }

    }
}

fun addMovieListScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(route =NavRoute.Characters.path ) {
        MovieListScreen{ id->
            navController.navigate(NavRoute.CharacterDetail.withArgs(id.toString()))
        }
    }

}
