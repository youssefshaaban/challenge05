package com.banquemisr.domain.entity.movie

data class Movie(
    val id: Int,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val runtime: Int? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val genres: List<Genres>?=null
)

data class Genres(val id: Int, val name: String)
