package com.banquemisr.data.di

import com.banquemisr.data.di.qulifier.MovieDataMapper
import com.banquemisr.data.mapper.MoviePageDataMapper
import com.banquemisr.data.model.movie_list.MovieResponse
import com.banquemisr.domain.DataMapper
import com.banquemisr.domain.entity.movie.PageData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object MapperModule {

    @Provides
    @MovieDataMapper
    fun provideMoviePageDataMapper(): DataMapper<MovieResponse, PageData> {
        return MoviePageDataMapper()
    }


}