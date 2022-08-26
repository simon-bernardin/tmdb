package com.mobiapps.courses.tmdb.pages.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mobiapps.courses.tmdb.R
import com.mobiapps.courses.tmdb.entities.Movie

class MoviesListAdapter(private val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    var dataSet: List<Movie> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster: ImageView
        val title: TextView
        val releaseDate: TextView

        init {
            with(view) {
                poster = findViewById(R.id.backdrop)
                title = findViewById(R.id.title)
                releaseDate = findViewById(R.id.releaseDate)
                setOnClickListener {
                    onClick(dataSet[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.poster.load("https://image.tmdb.org/t/p/original/${dataSet[position].posterUrl}")
        holder.title.text = dataSet[position].title
        holder.releaseDate.text = dataSet[position].releaseDate.toString()
    }

    override fun getItemCount(): Int =
        dataSet.size

}