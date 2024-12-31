package com.banquemisr.domain.usecases.movielist_usecase

import com.banquemisr.domain.entity.movie.PageData
import com.banquemisr.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetMovieListUseCase {
    suspend operator fun invoke(page:Int): Flow<Resource<PageData>>
}