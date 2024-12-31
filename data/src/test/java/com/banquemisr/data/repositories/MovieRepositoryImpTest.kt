package com.banquemisr.data.repositories


import com.banquemisr.data.mapper.MoviePageDataMapper
import com.banquemisr.data.model.movie_list.MovieResponse
import com.banquemisr.data.remote.MovieAPI
import com.banquemisr.data.util.TestUtil.createMockGetMovieResponse
import com.banquemisr.domain.DataMapper
import com.banquemisr.domain.entity.QueryCharacters
import com.banquemisr.domain.entity.movie.PageData
import com.banquemisr.domain.util.Resource
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MovieRepositoryImpTest {
    private val movieAPI: MovieAPI = mockk()
    private val dataMapper: DataMapper<MovieResponse, PageData> = MoviePageDataMapper()
    private lateinit var movieRepositoryImp: MovieRepositoryImp

    @Before
    fun setUp() {
        movieRepositoryImp = MovieRepositoryImp(movieAPI, dataMapper)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getMovieListNowPlaying should return success when API call succeeds`() = runTest {
        // Mock API response
        val mockResponse = createMockGetMovieResponse()
        coEvery { movieAPI.getMoviesNowPlayingList(any()) } returns mockResponse
        val queryCharacters = QueryCharacters()
        // Collect and assert the result
        val result =
            movieRepositoryImp.getMovieListNowPlaying( queryCharacters).first()
        assert(result is Resource.Success)
        assertEquals(
            dataMapper.execute(mockResponse.body()!!),
            (result as Resource.Success).data
        )

        coVerify { movieAPI.getMoviesNowPlayingList(queryCharacters.toQueryMap()) }
    }


    @Test
    fun `getMovieListNowPlaying should return error when API call fails`() = runTest {
        // Mock API failure
        coEvery { movieAPI.getMoviesNowPlayingList(any()) } returns Response.error(
            500,
            "str".toResponseBody()
        )

        val queryCharacters = QueryCharacters()
        // Collect and assert the result
        val result = movieRepositoryImp.getMovieListNowPlaying(queryCharacters).first()
        assert(result is Resource.Error)
        coVerify { movieAPI.getMoviesNowPlayingList(   queryCharacters.toQueryMap()) }
    }

    @Test
    fun `getMoviesPopularList should return success when API call succeeds`() = runTest {
        // Mock API response
        val mockResponse = createMockGetMovieResponse()
        coEvery { movieAPI.getMoviesPopularList(any()) } returns mockResponse
        val queryCharacters = QueryCharacters()
        // Collect and assert the result
        val result =
            movieRepositoryImp.getMovieListPopular( queryCharacters).first()
        assert(result is Resource.Success)
        assertEquals(
            dataMapper.execute(mockResponse.body()!!),
            (result as Resource.Success).data
        )

        coVerify { movieAPI.getMoviesPopularList(queryCharacters.toQueryMap()) }
    }


    @Test
    fun `getMoviesPopularList should return error when API call fails`() = runTest {
        // Mock API failure
        coEvery { movieAPI.getMoviesPopularList(any()) } returns Response.error(
            500,
            "str".toResponseBody()
        )

        val queryCharacters = QueryCharacters()
        // Collect and assert the result
        val result = movieRepositoryImp.getMovieListPopular(queryCharacters).first()
        assert(result is Resource.Error)
        coVerify { movieAPI.getMoviesPopularList(   queryCharacters.toQueryMap()) }
    }

    @Test
    fun `getMovieListUpcoming should return success when API call succeeds`() = runTest {
        // Mock API response
        val mockResponse = createMockGetMovieResponse()
        coEvery { movieAPI.getMoviesUpcomingList(any()) } returns mockResponse
        val queryCharacters = QueryCharacters()
        // Collect and assert the result
        val result =
            movieRepositoryImp.getMovieListUpcoming( queryCharacters).first()
        assert(result is Resource.Success)
        assertEquals(
            dataMapper.execute(mockResponse.body()!!),
            (result as Resource.Success).data
        )

        coVerify { movieAPI.getMoviesUpcomingList(queryCharacters.toQueryMap()) }
    }


    @Test
    fun `getMovieListUpcoming should return error when API call fails`() = runTest {
        // Mock API failure
        coEvery { movieAPI.getMoviesUpcomingList(any()) } returns Response.error(
            500,
            "str".toResponseBody()
        )

        val queryCharacters = QueryCharacters()
        // Collect and assert the result
        val result = movieRepositoryImp.getMovieListUpcoming(queryCharacters).first()
        assert(result is Resource.Error)
        coVerify { movieAPI.getMoviesUpcomingList(   queryCharacters.toQueryMap()) }
    }
}