package com.mobiapps.courses.tmdb.datasources.remote.dtos

data class MoviesListDto(
    val page: Int,
    val results: List<MoviesListResultDto>,
    val total_pages: Int,
    val total_results: Int
)
