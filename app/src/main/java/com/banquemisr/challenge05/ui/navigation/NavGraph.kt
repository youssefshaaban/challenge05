package com.banquemisr.challenge05.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.banquemisr.challenge05.ui.movie_detail.MovieDetailScreen
import com.banquemisr.challenge05.ui.movies.MovieListScreen

@Composable
fun NavGraph(modifier: Modifier=Modifier,navController: NavHostController) {
    NavHost(modifier =modifier,
        navController = navController,
        startDestination = NavRoute.MovieList.path
    ) {
        addMovieListScreen(navController, this)

        addMovieDetailScreen(navController, this)
    }
}

fun addMovieDetailScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(route = NavRoute.MovieDetail.withArgsFormat(NavRoute.MovieDetail.movieId)) { navBackStackEntry->
        val movieId = navBackStackEntry.arguments?.getString(NavRoute.MovieDetail.movieId,"")
        /* We check if it's not null */
        movieId?.let { id->
            MovieDetailScreen(id)
        }

    }
}

fun addMovieListScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(route =NavRoute.MovieList.path ) {
        MovieListScreen{ id->
            navController.navigate(NavRoute.MovieDetail.withArgs(id.toString()))
        }
    }

}
