package com.mobiapps.courses.tmdb.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mobiapps.courses.tmdb.R
import com.mobiapps.courses.tmdb.services.TmdbService

class MovieDetailActivity : AppCompatActivity() {
    private val tmdbService: TmdbService = TmdbService()
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
    }

    override fun onStart() {
        super.onStart()
        movie = tmdbService.getMovieDetail(999)

        findViewById<TextView>(R.id.title).text = movie.title
        findViewById<TextView>(R.id.vote).text = getString(R.string.votes, movie.averageVote.toString(), movie.votesNumber.toString())
        findViewById<TextView>(R.id.overview).text = movie.overview
        findViewById<TextView>(R.id.cast).text = movie.cast.map { it.name }.joinToString(" - ")
    }
}