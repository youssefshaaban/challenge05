package com.banquemisr.data.repositories

import com.banquemisr.data.di.qulifier.MovieDataMapper
import com.banquemisr.data.model.movie_list.MovieResponse
import com.banquemisr.data.remote.MovieAPI
import com.banquemisr.data.utils.apiCall
import com.banquemisr.domain.DataMapper
import com.banquemisr.domain.entity.QueryCharacters
import com.banquemisr.domain.entity.movie.PageData
import com.banquemisr.domain.repositories.IMoviesRepository
import com.banquemisr.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(
    private val movieAPI: MovieAPI,
    private val dataMapper: DataMapper<MovieResponse, PageData>
) : IMoviesRepository {
    override suspend fun getMovieListNowPlaying(queryCharacters: QueryCharacters): Flow<Resource<PageData>> {
        return flow {
            val result = apiCall { movieAPI.getMoviesNowPlayingList( queryCharacters.toQueryMap()) }
            when (result) {
                is Resource.Success -> {
                    emit(Resource.Success(dataMapper.execute(result.data)))
                }

                is Resource.Error -> {
                    emit(Resource.Error(result.error))
                }
            }
        }
    }

    override suspend fun getMovieListUpcoming(queryCharacters: QueryCharacters): Flow<Resource<PageData>> {
        return flow {
            val result = apiCall { movieAPI.getMoviesUpcomingList( queryCharacters.toQueryMap()) }
            when (result) {
                is Resource.Success -> {
                    emit(Resource.Success(dataMapper.execute(result.data)))
                }

                is Resource.Error -> {
                    emit(Resource.Error(result.error))
                }
            }
        }
    }

    override suspend fun getMovieListPopular(queryCharacters: QueryCharacters): Flow<Resource<PageData>> {
        return flow {
            val result = apiCall { movieAPI.getMoviesPopularList( queryCharacters.toQueryMap()) }
            when (result) {
                is Resource.Success -> {
                    emit(Resource.Success(dataMapper.execute(result.data)))
                }

                is Resource.Error -> {
                    emit(Resource.Error(result.error))
                }
            }
        }
    }
}