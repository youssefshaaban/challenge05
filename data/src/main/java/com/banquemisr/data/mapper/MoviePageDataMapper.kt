package com.banquemisr.data.mapper

import com.banquemisr.data.model.movie_list.MovieResponse
import com.banquemisr.domain.DataMapper
import com.banquemisr.domain.entity.movie.Movie
import com.banquemisr.domain.entity.movie.PageData
import javax.inject.Inject

class MoviePageDataMapper @Inject constructor() : DataMapper<MovieResponse, PageData> {
    override fun execute(data: MovieResponse): PageData {
        return PageData(
            page = data.page,
            total_pages = data.total_pages,
            total_results = data.total_results,
            results = data.results.map {
                Movie(
                    id = it.id,
                    original_title = it.original_title,
                    title = it.title,
                    poster_path = it.poster_path,
                    overview = it.overview,
                    release_date = it.release_date
                )
            })
    }
}