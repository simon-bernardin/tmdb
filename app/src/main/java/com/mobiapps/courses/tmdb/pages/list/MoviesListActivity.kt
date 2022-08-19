package com.mobiapps.courses.tmdb.pages.list

import android.content.Intent
import android.os.Build
import android.graphics.Rect
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
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

        val moviesList = findViewById<RecyclerView>(R.id.moviesList)

        val moviesListAdapter = MoviesListAdapter() {
            navigateToDetail(it)
        }

//        val divider: ItemDecoration = object : ItemDecoration() {
//            override fun getItemOffsets(
//                outRect: Rect,
//                view: View,
//                parent: RecyclerView,
//                state: RecyclerView.State
//            ) {
//                outRect.setEmpty()
//            }
//        }
//        moviesList.addItemDecoration(divider)

        moviesList.adapter = moviesListAdapter
        moviesList.layoutManager = LinearLayoutManager(this)

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