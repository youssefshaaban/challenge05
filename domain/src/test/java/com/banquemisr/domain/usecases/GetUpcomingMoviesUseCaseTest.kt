package com.banquemisr.domain.usecases

import com.banquemisr.domain.entity.QueryCharacters
import com.banquemisr.domain.repositories.IMoviesRepository
import com.banquemisr.domain.usecases.movielist_usecase.GetUpcomingMoviesUseCase
import com.banquemisr.domain.util.TestUtil
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetUpcomingMoviesUseCaseTest{
    private lateinit var iMoviesRepository: IMoviesRepository

    @Before
    fun setUp() {

        iMoviesRepository = mockk()
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    operator fun invoke() {
        runTest {
            val queryCharacters= QueryCharacters()
            val mockResult= TestUtil.createMovieList()
            coEvery { iMoviesRepository.getMovieListUpcoming(any()) } returns mockResult
            val result = GetUpcomingMoviesUseCase(iMoviesRepository).invoke(queryCharacters.page)
            coVerify { iMoviesRepository.getMovieListUpcoming(any()) }
            assertEquals(result,mockResult)
        }
    }
}