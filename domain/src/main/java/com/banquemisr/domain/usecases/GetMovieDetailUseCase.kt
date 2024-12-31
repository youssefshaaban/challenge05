package com.banquemisr.domain.usecases

import com.banquemisr.domain.entity.movie.Movie
import com.banquemisr.domain.repositories.IMoviesRepository
import com.banquemisr.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val iMoviesRepository: IMoviesRepository) {
     suspend operator fun invoke(movieId:String): Flow<Resource<Movie>> {
        return iMoviesRepository.getMovieDetail(movieId)
    }
}