package com.banquemisr.domain.usecases.movielist_usecase

import com.banquemisr.domain.entity.MovieType
import com.banquemisr.domain.repositories.IMoviesRepository
import javax.inject.Inject

class MovieListByTypeUsCaseImp @Inject constructor(val iMoviesRepository: IMoviesRepository){
     fun createMovieList(type: MovieType): GetMovieListUseCase {
        return when(type){
             MovieType.UPCOMING->{
                 GetUpcomingMoviesUseCase(iMoviesRepository = iMoviesRepository)
             }
            MovieType.POPULAR->{
                GetPopularMoviesUseCase(iMoviesRepository = iMoviesRepository)
            }
            MovieType.NOW_PLAYING->{
                GetNowPlayingMoviesUseCase(iMoviesRepository = iMoviesRepository)
            }
        }
    }
}