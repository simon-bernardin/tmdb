package com.mobiapps.courses.tmdb.pages

import com.mobiapps.courses.tmdb.entities.CastMember

data class Movie(
    var id: Int = -1,
    var posterUrl: String? = null,
    var title: String? = null,
    var averageVote: Float = 0f,
    var votesNumber: Int = 0,
    var overview: String? = null,
    var cast: List<CastMember> = emptyList()
)
