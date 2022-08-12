package com.mobiapps.courses.tmdb.entities

import java.time.LocalDate

data class Movie(
    var id: Int = -1,
    var posterUrl: String? = null,
    var backdropUrl: String? = null,
    var title: String? = null,
    var averageVote: Float = 0f,
    var votesNumber: Int = 0,
    var overview: String? = null,
    var cast: List<CastMember> = emptyList(),
    var releaseDate: LocalDate? = null
)
