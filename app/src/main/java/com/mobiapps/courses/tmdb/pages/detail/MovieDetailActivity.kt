package com.mobiapps.courses.tmdb.pages.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import androidx.annotation.RequiresApi
import com.mobiapps.courses.tmdb.R
import com.mobiapps.courses.tmdb.entities.Movie
import com.mobiapps.courses.tmdb.services.TmdbService

class MovieDetailActivity : AppCompatActivity() {
    private val tmdbService: TmdbService = TmdbService()

    companion object {
        const val INTENT_PARAM_ID = "intent_param_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        val id = intent.getIntExtra(INTENT_PARAM_ID, -1)
        tmdbService.getMovieDetail(id, success = { movie ->
            movie?.let {
                findViewById<ImageView>(R.id.backdrop).load("https://image.tmdb.org/t/p/original/${it.backdropUrl}")
                findViewById<TextView>(R.id.title).text = it.title
                findViewById<TextView>(R.id.vote).text = getString(
                    R.string.votes,
                    it.averageVote.toString(),
                    it.votesNumber.toString()
                )
                findViewById<TextView>(R.id.overview).text = it.overview
                findViewById<TextView>(R.id.cast).text = it.cast.map { it.name }.joinToString(" - ")
            }
        }, failure = {

        })
    }
}