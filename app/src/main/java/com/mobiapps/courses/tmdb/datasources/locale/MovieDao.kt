package com.mobiapps.courses.tmdb.datasources.locale

import androidx.room.*
import com.mobiapps.courses.tmdb.entities.Movie

@Dao
interface MovieDao {
    @Query("select * from movie")
    fun getAll(): List<Movie>

    @Query("select * from movie where id=:id")
    fun getById(id: Int): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(vararg movie: Movie)

    @Delete
    fun removeFavorite(movie: Movie)
}
