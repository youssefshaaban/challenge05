package com.banquemisr.domain.usecases

import com.banquemisr.domain.entity.QueryCharacters
import com.banquemisr.domain.repositories.IMoviesRepository
import com.banquemisr.domain.usecases.movielist_usecase.GetNowPlayingMoviesUseCase
import com.banquemisr.domain.util.TestUtil
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetNowPlayingMoviesUseCaseTest {
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
            val queryCharacters=QueryCharacters()
            val mockResult=TestUtil.createMovieList()
            coEvery { iMoviesRepository.getMovieListNowPlaying(any()) } returns mockResult
            val result = GetNowPlayingMoviesUseCase(iMoviesRepository).invoke(queryCharacters.page)
            coVerify { iMoviesRepository.getMovieListNowPlaying(any()) }
            assertEquals(result,mockResult)
        }
    }
}