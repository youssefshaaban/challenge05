package com.banquemisr.data.di

import com.banquemisr.data.di.qulifier.MovieDataMapper
import com.banquemisr.data.mapper.MoviePageDataMapper
import com.banquemisr.data.model.movie_list.MovieResponse
import com.banquemisr.data.remote.MovieAPI
import com.banquemisr.data.repositories.MovieRepositoryImp
import com.banquemisr.domain.DataMapper
import com.banquemisr.domain.entity.movie.PageData
import com.banquemisr.domain.repositories.IMoviesRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {


    @Provides
    fun provideCharactersRepository(
        movieAPI: MovieAPI,
        @MovieDataMapper  dataMapper: DataMapper<MovieResponse,PageData>
    ): IMoviesRepository {
        return MovieRepositoryImp(movieAPI,dataMapper)
    }



}