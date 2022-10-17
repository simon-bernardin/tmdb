package com.mobiapps.courses.tmdb.pages.list

import android.util.Log
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobiapps.courses.tmdb.R
import com.mobiapps.courses.tmdb.entities.Movie
import com.mobiapps.courses.tmdb.pages.detail.MovieDetailActivity
import com.mobiapps.courses.tmdb.services.TmdbService


class MoviesListActivity : AppCompatActivity() {
    private lateinit var tmdbService: TmdbService
    private lateinit var moviesListAdapter: MoviesListAdapter
    private var moviesList: List<Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        tmdbService = TmdbService(applicationContext)

        moviesList = savedInstanceState?.getParcelableArrayList("MOVIES_LIST")
    }

    override fun onStart() {
        super.onStart()

        val moviesListView = findViewById<RecyclerView>(R.id.moviesList)

        moviesListAdapter = MoviesListAdapter({
            navigateToDetail(it)
        }, { movie, favorite ->
            tmdbService.toggleFavorite(movie, favorite)
        })

        moviesListView.adapter = moviesListAdapter
        moviesListView.layoutManager = GridLayoutManager(this, 3)

        if (moviesList == null) {
            tmdbService.getLatestMovies(success = {
                runOnUiThread {
                    moviesListAdapter.dataSet = it
                }
            }, failure = {})
        } else {
            moviesListAdapter.dataSet = moviesList as List<Movie>
        }

        val searchMovie = findViewById<EditText>(R.id.searchMovie)
        searchMovie.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                Log.d("searchMovie", "$s")
                tmdbService.getMoviesByName(s.toString(), success = {
                    runOnUiThread {
                        moviesListAdapter.dataSet = it
                    }
                }, failure = {})
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("MOVIES_LIST", ArrayList(moviesListAdapter.dataSet))
        super.onSaveInstanceState(outState)
    }

    private fun navigateToDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.INTENT_PARAM_ID, movie.id)
        startActivity(intent)
    }
}