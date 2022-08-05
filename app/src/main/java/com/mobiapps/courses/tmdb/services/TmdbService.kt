package com.mobiapps.courses.tmdb.services

import com.mobiapps.courses.tmdb.datasources.MockDataSource
import com.mobiapps.courses.tmdb.entities.CastMember
import com.mobiapps.courses.tmdb.entities.Movie

class TmdbService {
    private val dataSource = MockDataSource()

    fun getMovieDetail(id: Int): Movie? {
        return dataSource.movies.find { it.id == id }
    }

    fun getLatestMovies(): List<Movie> {
        return dataSource.movies
    }
}