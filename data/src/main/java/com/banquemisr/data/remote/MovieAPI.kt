package com.banquemisr.data.remote

import com.banquemisr.data.model.movie_list.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface MovieAPI {

    @GET("3/movie/popular")
    suspend fun getMoviesPopularList(
        @QueryMap queryMap: Map<String, String>
    ): Response<MovieResponse>

    @GET("3/movie/now_playing")
    suspend fun getMoviesNowPlayingList(
        @QueryMap queryMap: Map<String, String>
    ): Response<MovieResponse>

    @GET("3/movie/upcoming")
    suspend fun getMoviesUpcomingList(
        @QueryMap queryMap: Map<String, String>
    ): Response<MovieResponse>


}