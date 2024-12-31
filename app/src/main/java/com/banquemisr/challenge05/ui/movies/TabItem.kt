package com.banquemisr.challenge05.ui.movies

import com.banquemisr.domain.entity.MovieType

data class TabItem(val movieType: MovieType, val name:String)

val tabs= listOf(TabItem(MovieType.NOW_PLAYING,"Now playing"),TabItem(MovieType.UPCOMING,"Upcoming"),TabItem(MovieType.POPULAR,"Popular"))