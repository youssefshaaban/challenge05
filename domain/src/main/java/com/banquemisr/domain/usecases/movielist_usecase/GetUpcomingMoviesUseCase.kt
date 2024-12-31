package com.banquemisr.domain.usecases.movielist_usecase

import com.banquemisr.domain.entity.MovieType
import com.banquemisr.domain.entity.QueryCharacters
import com.banquemisr.domain.entity.movie.PageData
import com.banquemisr.domain.repositories.IMoviesRepository
import com.banquemisr.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(private val iMoviesRepository: IMoviesRepository):GetMovieListUseCase {
    override suspend operator fun invoke(page:Int): Flow<Resource<PageData>> {
        return iMoviesRepository.getMovieListUpcoming(QueryCharacters(page=page))
    }
}