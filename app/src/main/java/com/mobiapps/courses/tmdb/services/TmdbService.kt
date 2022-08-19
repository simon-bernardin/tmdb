package com.mobiapps.courses.tmdb.services

import android.os.Build
import androidx.annotation.RequiresApi
import com.mobiapps.courses.tmdb.datasources.MockDataSource
import com.mobiapps.courses.tmdb.entities.Movie

class TmdbService {
    private val dataSource = MockDataSource()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMovieDetail(id: Int, success: (movie: Movie?) -> Unit, failure: () -> Unit) {
        success(dataSource.movies.find { it.id == id })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getLatestMovies(success: (movies: List<Movie>) -> Unit, failure: () -> Unit) {
        success(dataSource.movies)
    }
}