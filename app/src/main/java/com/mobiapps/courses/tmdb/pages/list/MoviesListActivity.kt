package com.mobiapps.courses.tmdb.pages.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobiapps.courses.tmdb.R
import com.mobiapps.courses.tmdb.entities.Movie
import com.mobiapps.courses.tmdb.pages.detail.MovieDetailActivity
import com.mobiapps.courses.tmdb.services.TmdbService

class MoviesListActivity : AppCompatActivity() {
    private lateinit var tmdbService: TmdbService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        tmdbService = TmdbService(applicationContext)
    }

    override fun onStart() {
        super.onStart()

        val moviesList = findViewById<RecyclerView>(R.id.moviesList)

        val moviesListAdapter = MoviesListAdapter ({
            navigateToDetail(it)
        }, { movie, favorite ->
            tmdbService.toggleFavorite(movie, favorite)
        })

        moviesList.adapter = moviesListAdapter
        moviesList.layoutManager = GridLayoutManager(this, 3)

        tmdbService.getLatestMovies(success = {
            runOnUiThread {
                moviesListAdapter.dataSet = it
            }
        }, failure = {})


    }

    private fun navigateToDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.INTENT_PARAM_ID, movie.id)
        startActivity(intent)
    }
}