package com.mobiapps.courses.tmdb.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Movie @Ignore constructor(
    @PrimaryKey val id: Int = -1,
    val posterUrl: String? = null,
    val backdropUrl: String? = null,
    val title: String? = null,
    val averageVote: Float = 0f,
    val votesNumber: Int = 0,
    val overview: String? = null,
    val releaseDate: LocalDate? = null,
    @Ignore
    var favorite: Boolean = false,
    @Ignore
    val cast: List<CastMember> = emptyList(),
) {
    constructor(
        id: Int,
        posterUrl: String?,
        backdropUrl: String?,
        title: String?,
        averageVote: Float,
        votesNumber: Int,
        overview: String?,
        releaseDate: LocalDate?,
    ) : this(
        id,
        posterUrl,
        backdropUrl,
        title,
        averageVote,
        votesNumber,
        overview,
        releaseDate,
        false,
        emptyList()
    )
}

