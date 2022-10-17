package com.mobiapps.courses.tmdb.datasources.remote

import com.mobiapps.courses.tmdb.datasources.remote.dtos.MovieDto
import com.mobiapps.courses.tmdb.datasources.remote.dtos.MoviesListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {
    @GET("movie/now_playing")
    suspend fun getLatestMovies(): Call<MoviesListDto>

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: Int): Call<MovieDto>

    @GET("search/movie")
    suspend fun getMovieByName(@Query("query") query: String): Call<MoviesListDto>
}