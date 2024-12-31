package com.banquemisr.challenge05.ui.movies

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banquemisr.domain.entity.MovieType
import com.banquemisr.domain.entity.movie.Movie
import com.banquemisr.domain.usecases.movielist_usecase.MovieListByTypeUsCaseImp
import com.banquemisr.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieListByTypeUsCaseImp: MovieListByTypeUsCaseImp) :
    ViewModel() {
    private val _movieState = mutableStateMapOf<String, PersistentList<Movie>>()
    val movieState: MutableMap<String, PersistentList<Movie>> = _movieState
    private val _movieEroreState = mutableStateMapOf<String, String>()
    val movieEroreState: MutableMap<String, String> = _movieEroreState
    private val _loadingState = mutableStateOf(false)
    val loadingState: MutableState<Boolean> = _loadingState

    fun fetchMoviesByType(movieType: MovieType) {
        if (_movieState.containsKey(movieType.type)) return // Avoid re-fetching if already fetched

        _loadingState.value = true

        viewModelScope.launch {
            movieListByTypeUsCaseImp.createMovieList(movieType).invoke(1)
                .collectLatest { result ->
                    when (result) {
                        is Resource.Success -> {
                            _movieState += (movieType.type to result.data.results.toPersistentList())
                        }

                        is Resource.Error -> {
                            _movieEroreState += (movieType.type to "Something wrong ${movieType.type}")
                        }
                    }
                    _loadingState.value = false
                }

        }
    }

}