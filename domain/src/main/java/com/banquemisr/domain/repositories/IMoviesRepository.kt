package com.banquemisr.domain.repositories

import com.banquemisr.domain.entity.QueryCharacters
import com.banquemisr.domain.entity.movie.Movie
import com.banquemisr.domain.entity.movie.PageData
import com.banquemisr.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {

    suspend fun getMovieListNowPlaying(queryCharacters: QueryCharacters):Flow<Resource<PageData>>

    suspend fun getMovieListUpcoming(queryCharacters: QueryCharacters):Flow<Resource<PageData>>

    suspend fun getMovieListPopular(queryCharacters: QueryCharacters):Flow<Resource<PageData>>

    suspend fun getMovieDetail(movieId:String):Flow<Resource<Movie>>
}