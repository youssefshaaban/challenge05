package com.banquemisr.domain.util

import com.banquemisr.domain.entity.movie.PageData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object TestUtil {



    fun createMovieList(): Flow<Resource<PageData>> {
        // Create a map of currencies for the mock response


        // Create a Response object with your mock data
        return flowOf(Resource.Success(data = PageData(page = 1, results = emptyList(), total_pages = 2, total_results = 33)))
    }



}