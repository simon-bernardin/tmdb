package com.mobiapps.courses.tmdb.datasources

import com.mobiapps.courses.tmdb.datasources.dtos.LatestMoviesDto
import retrofit2.Response
import retrofit2.http.GET

interface TmdbService {
    @GET("movie/now_playing")
    suspend fun getLatestMovies(): Response<LatestMoviesDto>
}