package com.banquemisr.data.mapper

import com.banquemisr.data.model.movie_detail.MovieDetailResponse
import com.banquemisr.domain.DataMapper
import com.banquemisr.domain.entity.movie.Genres
import com.banquemisr.domain.entity.movie.Movie
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() : DataMapper<MovieDetailResponse, Movie> {
    override fun execute(data: MovieDetailResponse): Movie {
        return Movie(id=data.id, genres = data.genres.map { Genres(it.id,it.name) }, title = data.title, original_title = data.original_title, poster_path = data.poster_path, release_date = data.release_date, overview = data.overview)
    }
}