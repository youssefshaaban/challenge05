package com.banquemisr.challenge05.ui.movie_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banquemisr.domain.entity.movie.Movie
import com.banquemisr.domain.usecases.GetMovieDetailUseCase
import com.banquemisr.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase) :
    ViewModel() {
    private val _movieDataState = MutableStateFlow(MovieDetailViewState(loading = true))
    val movieDataState: StateFlow<MovieDetailViewState> = _movieDataState

    fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
            getMovieDetailUseCase(movieId)
                .collectLatest { result ->
                    when (result) {
                        is Resource.Success -> {
                            _movieDataState.value=_movieDataState.value.copy(loading = false, data = result.data)
                        }

                        is Resource.Error -> {
                            _movieDataState.value=_movieDataState.value.copy(loading = false, error = "Something wrong")
                        }
                    }
                }

        }
    }

}

data class MovieDetailViewState(
    val loading: Boolean,
    val data: Movie? = null,
    val error: String? = null
)