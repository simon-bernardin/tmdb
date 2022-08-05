package com.mobiapps.courses.tmdb.services

import com.mobiapps.courses.tmdb.entities.CastMember
import com.mobiapps.courses.tmdb.pages.Movie

class TmdbService {
    fun getMovieDetail(id: Int): Movie {
        return Movie(
            id = id,
            title = "Interstellar",
            averageVote = 8.9f,
            votesNumber = 9999,
            overview = "Best sci-fi movie ever",
            cast = listOf(
                CastMember(name = "Matthew McConaughey"),
                CastMember(name = "Jessica Chastain"),
                CastMember(name = "Anne Hathaway"),
                CastMember(name = "Michael Caine"),
            )
        )
    }
}