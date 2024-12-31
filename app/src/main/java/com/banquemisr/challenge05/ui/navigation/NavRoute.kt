package com.banquemisr.challenge05.ui.navigation


sealed class NavRoute(val path: String) {

    data object MovieList: NavRoute("movies-list")


    data object MovieDetail: NavRoute("movieDetail") {
        val movieId = "movieId"
    }



    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String) : String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/{$arg}")
            }
        }
    }
}

