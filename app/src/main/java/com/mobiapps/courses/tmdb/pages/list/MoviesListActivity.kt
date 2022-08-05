package com.mobiapps.courses.tmdb.pages.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobiapps.courses.tmdb.R
import com.mobiapps.courses.tmdb.entities.Movie
import com.mobiapps.courses.tmdb.pages.detail.MovieDetailActivity
import com.mobiapps.courses.tmdb.services.TmdbService

class MoviesListActivity : AppCompatActivity() {
    private val tmdbService: TmdbService = TmdbService()
    private lateinit var movies: List<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
    }

    override fun onStart() {
        super.onStart()

        movies = tmdbService.getLatestMovies()

        val moviesList = findViewById<RecyclerView>(R.id.moviesList)
        moviesList.adapter = MoviesListAdapter(movies) {
            navigateToDetail(it)
        }
        moviesList.layoutManager = LinearLayoutManager(this)
    }

    private fun navigateToDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.INTENT_PARAM_ID, movie.id)
        startActivity(intent)
    }
}