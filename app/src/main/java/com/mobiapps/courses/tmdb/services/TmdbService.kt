package com.mobiapps.courses.tmdb.services

import com.mobiapps.courses.tmdb.datasources.MockDataSource
import com.mobiapps.courses.tmdb.datasources.NetworkDataSource
import com.mobiapps.courses.tmdb.entities.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TmdbService(private val mock: Boolean = false) {
    private val mockDataSource = MockDataSource()
    private val networkDataSource = NetworkDataSource()

    fun getMovieDetail(id: Int, success: (movie: Movie?) -> Unit, failure: () -> Unit) {
        if (mock)
            success(mockDataSource.movies.find { it.id == id })
        else
            CoroutineScope(Dispatchers.IO).launch {
                networkDataSource.getMovieById(id, success = {
                    success(it)
                }, failure = {
                    failure()
                })
            }
    }

    fun getLatestMovies(success: (movies: List<Movie>) -> Unit, failure: () -> Unit) {
        if (mock)
            success(mockDataSource.movies)
        else {
            CoroutineScope(Dispatchers.IO).launch {
                networkDataSource.getLatestMovies(success = {
                    success(it)
                }, failure = {
                    failure()
                })
            }
        }
    }
}