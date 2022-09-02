package com.mobiapps.courses.tmdb.datasources.remote.dtos

data class LatestMoviesDto(
    val page: Int,
    val results: List<LatestMoviesResultDto>,
    val total_pages: Int,
    val total_results: Int
)
