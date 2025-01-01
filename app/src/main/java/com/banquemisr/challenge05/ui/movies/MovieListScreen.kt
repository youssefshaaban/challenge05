package com.banquemisr.challenge05.ui.movies

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.banquemisr.challenge05.ui.components.Loading
import com.banquemisr.challenge05.utils.base_image
import com.banquemisr.domain.entity.movie.Movie
import kotlinx.collections.immutable.PersistentList

@Composable
fun MovieListScreen(onClickMovie: (Int) -> Unit) {
    val viewModel = hiltViewModel<MovieListViewModel>()

    // Keep track of the selected tab index
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Fetch posts when a tab is selected
    LaunchedEffect(selectedTabIndex) {
        val category = tabs[selectedTabIndex]
        Log.d("MovieScreen",category.toString())
        viewModel.fetchMoviesByType(category.movieType)
    }

    val movieState = viewModel.movieState
    val movieStateError = viewModel.movieEroreState
    val loadingState = viewModel.loadingState.value
    Column {
        // Tab row
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth().padding(15.dp)
        ) {
            tabs.forEachIndexed { index, category ->
                Tab(
                    modifier = Modifier.padding(10.dp),
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(category.name) }
                )
            }
        }

        // Tab content
        Box(modifier = Modifier.fillMaxSize().padding(top = 10.dp)) {
            if (loadingState) {
                Loading(Modifier.align(Alignment.Center))
            } else {
                val movies = movieState[tabs[selectedTabIndex].movieType.type]
                val moviesErorr = movieStateError[tabs[selectedTabIndex].movieType.type]
                if (!moviesErorr.isNullOrEmpty()){
                    Text(
                        text = moviesErorr,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else if (movies.isNullOrEmpty()) {
                    Text(
                        text = "No posts available.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    MovieList(movies = movies,onClickMovie)
                }
            }
        }

    }
}

@Composable
fun MovieList(movies: PersistentList<Movie>, onClickMovie:(Int)->Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(movies) {
            MovieItem(it,onClickMovie)
        }
    }
}


@Composable
fun MovieItem(movie: Movie, onClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                ), color = Color.White
            ).padding(10.dp)
            .clickable { onClick(movie.id) }
    ) {
        AsyncImage(
            model = "${base_image}/${movie.poster_path}",
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize().height(200.dp),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = movie.title,
            color = Color.Black,
            style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(5.dp)
        )
        Text(
            text = movie.release_date,
            color = Color.Black,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(5.dp)
        )
    }

}