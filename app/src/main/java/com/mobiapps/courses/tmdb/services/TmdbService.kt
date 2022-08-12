package com.mobiapps.courses.tmdb.services

import android.os.Build
import androidx.annotation.RequiresApi
import com.mobiapps.courses.tmdb.datasources.MockDataSource
import com.mobiapps.courses.tmdb.datasources.NetworkDataSource
import com.mobiapps.courses.tmdb.entities.Movie

class TmdbService(private val mock: Boolean = false) {
    private val mockDataSource = MockDataSource()
    private val networkDataSource = NetworkDataSource()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMovieDetail(id: Int, success: (movie: Movie?) -> Unit, failure: () -> Unit) {
        if (mock)
            success(mockDataSource.movies.find { it.id == id })
        else
            networkDataSource.getMovieById(id, success = {
                success(it)
            }, failure = {
                failure()
            })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getLatestMovies(success: (movies: List<Movie>) -> Unit, failure: () -> Unit) {
        if (mock)
            success(mockDataSource.movies)
        else {
            networkDataSource.getLatestMovies(success = {
                success(it)
            }, failure = {
                failure()
            })
        }
    }
}