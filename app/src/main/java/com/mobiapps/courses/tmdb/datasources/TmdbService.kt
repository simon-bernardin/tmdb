package com.mobiapps.courses.tmdb.datasources

import com.mobiapps.courses.tmdb.datasources.dtos.LatestMoviesDto
import com.mobiapps.courses.tmdb.datasources.dtos.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbService {
    @GET("movie/now_playing")
    suspend fun getLatestMovies(): Response<LatestMoviesDto>

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: Int): Response<MovieDto>
}