package com.banquemisr.challenge05.ui.movie_detail


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.banquemisr.challenge05.R
import com.banquemisr.challenge05.ui.components.Loading
import com.banquemisr.challenge05.utils.base_image

@Composable
fun MovieDetailScreen(movieId: String) {
    val viewModel = hiltViewModel<MovieDetailViewModel>()

    // Fetch posts when a tab is selected
    LaunchedEffect(Unit) {
        viewModel.getMovieDetail(movieId)
    }

    val movieState = viewModel.movieDataState.collectAsState().value
    Column(modifier = Modifier.fillMaxSize()) {
        if (movieState.loading){
            Loading(modifier = Modifier.fillMaxSize())
        }
        movieState.data?.let{movie->
            Column {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)){
                    AsyncImage(
                        model = "${base_image}/${movie.poster_path}",
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize().height(300.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Icon(painter = painterResource(R.drawable.back), modifier = Modifier.align(Alignment.TopStart).size(42.dp).padding(20.dp), contentDescription = "back")
                }
                Column (Modifier.padding(10.dp).weight(1f).scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)){
                    Text(movie.title, style = TextStyle(color = Color.Black, fontSize = 15.sp), modifier = Modifier.padding(5.dp))
                    movie.overview?.let {
                        Text(it, style = TextStyle(color = Color.Black, fontSize = 13.sp), modifier = Modifier.padding(5.dp))
                    }
                    movie.genres?.let {
                        Text(text = stringResource(R.string.genres), style = TextStyle(color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Bold))
                        it.forEach{gen->
                            Text(gen.name, style = TextStyle(fontSize = 12.sp), color = Color.Gray)
                        }
                    }


                }
            }
        }

        movieState.error?.let {
            Box(modifier = Modifier.fillMaxSize()){
                Text(it, modifier = Modifier.align(Alignment.Center))
            }
        }

    }
}