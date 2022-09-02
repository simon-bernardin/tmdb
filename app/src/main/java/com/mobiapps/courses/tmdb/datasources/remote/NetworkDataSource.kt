package com.mobiapps.courses.tmdb.datasources.remote

import android.util.Log
import androidx.annotation.WorkerThread
import com.mobiapps.courses.tmdb.entities.Movie
import com.mobiapps.courses.tmdb.entities.transformers.latestMoviesDtoToMoviesList
import com.mobiapps.courses.tmdb.entities.transformers.movieDtoFromMovie

class NetworkDataSource {
    @WorkerThread
    suspend fun getLatestMovies(success: (movies: List<Movie>) -> Unit, failure: () -> Unit) {
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

    @WorkerThread
    suspend fun getMovieById(id: Int, success: (movie: Movie) -> Unit, failure: () -> Unit) {
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