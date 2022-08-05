package com.mobiapps.courses.tmdb.datasources

import com.mobiapps.courses.tmdb.entities.CastMember
import com.mobiapps.courses.tmdb.entities.Movie

class MockDataSource {
    val movies: List<Movie> = listOf(
        Movie(
            id = 1,
            title = "Interstellar",
            averageVote = 8.6f,
            votesNumber = 9999,
            overview = "Best sci-fi movie ever",
            cast = listOf(
                CastMember(name = "Matthew McConaughey"),
                CastMember(name = "Jessica Chastain"),
                CastMember(name = "Anne Hathaway"),
                CastMember(name = "Michael Caine"),
            )
        ),
        Movie(
            id = 2,
            title = "The Batman",
            averageVote = 7.9f,
            votesNumber = 9999,
            overview = "Best Batman movie ever",
            cast = listOf(
                CastMember(name = "Robert Pattinson"),
                CastMember(name = "Zoe Kravitz"),
                CastMember(name = "Paul Dano"),
            )
        ),
        Movie(
            id = 3,
            title = "Fight Club",
            averageVote = 8.8f,
            votesNumber = 9999,
            overview = "Best movie ever",
            cast = listOf(
                CastMember(name = "Brad Pitt"),
                CastMember(name = "Edward Norton"),
                CastMember(name = "Helena Bonham Carter"),
            )
        )
    )
}