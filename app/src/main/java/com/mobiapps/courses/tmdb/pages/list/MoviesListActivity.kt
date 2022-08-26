package com.mobiapps.courses.tmdb.pages.list

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobiapps.courses.tmdb.R
import com.mobiapps.courses.tmdb.entities.Movie
import com.mobiapps.courses.tmdb.pages.detail.MovieDetailActivity
import com.mobiapps.courses.tmdb.services.TmdbService

class MoviesListActivity : AppCompatActivity() {
    private val tmdbService: TmdbService = TmdbService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        val moviesListAdapter = MoviesListAdapter() {
            navigateToDetail(it)
        }

        tmdbService.getLatestMovies(success = {
            moviesListAdapter.dataSet = it
        }, failure = {})

        val moviesList = findViewById<RecyclerView>(R.id.moviesList)

        moviesList.adapter = moviesListAdapter
        moviesList.layoutManager = LinearLayoutManager(this)
    }

    private fun navigateToDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.INTENT_PARAM_ID, movie.id)
        startActivity(intent)
    }
}