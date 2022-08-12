package com.mobiapps.courses.tmdb.datasources

import android.os.Build
import com.mobiapps.courses.tmdb.entities.Movie
import com.mobiapps.courses.tmdb.entities.transformers.latestMoviesDtoToMoviesList
import kotlinx.coroutines.*
import android.util.Log
import androidx.annotation.RequiresApi
import com.mobiapps.courses.tmdb.entities.transformers.movieDtoFromMovie

class NetworkDataSource {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(DelicateCoroutinesApi::class)
    fun getLatestMovies(success: (movies: List<Movie>) -> Unit, failure: () -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.tmdbService.getLatestMovies()

                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    content?.let {
                        success(latestMoviesDtoToMoviesList(it))
                    }
                } else
                    failure()
            } catch (e: Exception) {
                Log.d("NetworkDataSource", e.toString())
                failure()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getMovieById(id: Int, success: (movie: Movie) -> Unit, failure: () -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.tmdbService.getMovieById(id)

                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    content?.let {
                        success(movieDtoFromMovie(it))
                    }
                } else
                    failure()
            } catch (e: Exception) {
                Log.d("NetworkDataSource", e.toString())
                failure()
            }
        }
    }
}