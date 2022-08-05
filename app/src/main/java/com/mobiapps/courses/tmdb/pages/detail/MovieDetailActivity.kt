package com.mobiapps.courses.tmdb.pages.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mobiapps.courses.tmdb.R
import com.mobiapps.courses.tmdb.entities.Movie
import com.mobiapps.courses.tmdb.services.TmdbService

class MovieDetailActivity : AppCompatActivity() {
    private val tmdbService: TmdbService = TmdbService()
    private var movie: Movie? = null

    companion object {
        const val INTENT_PARAM_ID = "intent_param_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

    }

    override fun onStart() {
        super.onStart()

        val id = intent.getIntExtra(INTENT_PARAM_ID, -1)
        movie = tmdbService.getMovieDetail(id)

        movie?.let {
            findViewById<TextView>(R.id.title).text = it.title
            findViewById<TextView>(R.id.vote).text = getString(
                R.string.votes,
                it.averageVote.toString(),
                it.votesNumber.toString()
            )
            findViewById<TextView>(R.id.overview).text = it.overview
            findViewById<TextView>(R.id.cast).text = it.cast.map { it.name }.joinToString(" - ")
        }
    }
}