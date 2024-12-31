package com.banquemisr.data.di

import com.banquemisr.data.di.qulifier.MoviesDataMapper
import com.banquemisr.data.di.qulifier.MoviesDetailMapper
import com.banquemisr.data.mapper.MovieDetailsMapper
import com.banquemisr.data.mapper.MoviePageDataMapper
import com.banquemisr.data.model.movie_detail.MovieDetailResponse
import com.banquemisr.data.model.movie_list.MovieResponse
import com.banquemisr.domain.DataMapper
import com.banquemisr.domain.entity.movie.Movie
import com.banquemisr.domain.entity.movie.PageData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object MapperModule {

    @Provides
    @MoviesDataMapper
    fun provideMoviePageDataMapper(): DataMapper<MovieResponse, PageData> {
        return MoviePageDataMapper()
    }

    @Provides
    @MoviesDetailMapper
    fun provideMoviePageDetailMapper(): DataMapper<MovieDetailResponse, Movie> {
        return MovieDetailsMapper()
    }



}