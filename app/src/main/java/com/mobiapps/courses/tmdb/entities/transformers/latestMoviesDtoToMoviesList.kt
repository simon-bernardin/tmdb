package com.mobiapps.courses.tmdb.entities.transformers

import android.os.Build
import androidx.annotation.RequiresApi
import com.mobiapps.courses.tmdb.datasources.dtos.LatestMoviesDto
import com.mobiapps.courses.tmdb.entities.Movie
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun latestMoviesDtoToMoviesList(latestMoviesDto: LatestMoviesDto): List<Movie> {
    val movies = latestMoviesDto.results.map {
        Movie(
            id = it.id,
            posterUrl = it.poster_path,
            title = it.title,
            averageVote = it.vote_average,
            votesNumber = it.vote_count,
            overview = it.overview,
            releaseDate = LocalDate.parse(it.release_date),
        )
    }

    return movies
}