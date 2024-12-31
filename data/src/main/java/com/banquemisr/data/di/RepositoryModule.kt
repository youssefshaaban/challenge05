package com.banquemisr.data.di

import com.banquemisr.data.di.qulifier.MoviesDataMapper
import com.banquemisr.data.di.qulifier.MoviesDetailMapper
import com.banquemisr.data.model.movie_detail.MovieDetailResponse
import com.banquemisr.data.model.movie_list.MovieResponse
import com.banquemisr.data.remote.MovieAPI
import com.banquemisr.data.repositories.MovieRepositoryImp
import com.banquemisr.domain.DataMapper
import com.banquemisr.domain.entity.movie.Movie
import com.banquemisr.domain.entity.movie.PageData
import com.banquemisr.domain.repositories.IMoviesRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {


    @Provides
    fun provideCharactersRepository(
        movieAPI: MovieAPI,
        @MoviesDataMapper  dataMapper: DataMapper<MovieResponse,PageData>,
        @MoviesDetailMapper  movieMapper: DataMapper<MovieDetailResponse,Movie>
    ): IMoviesRepository {
        return MovieRepositoryImp(movieAPI,dataMapper,movieMapper)
    }



}