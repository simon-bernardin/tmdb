package com.mobiapps.courses.tmdb.datasources.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobiapps.courses.tmdb.datasources.locale.converters.LocalDateConverter
import com.mobiapps.courses.tmdb.entities.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}