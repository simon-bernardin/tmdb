package com.mobiapps.courses.tmdb.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class CastMember(
    val name: String? = null
) : Parcelable