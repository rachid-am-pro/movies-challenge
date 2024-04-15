package com.ramri.movies_challenge.ui.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ramri.MoviesChallenge.R
import com.ramri.movies_challenge.data.model.Movie

class MoviesAdapter(
    private var movieList: ArrayList<Movie>,
    private val context: Context
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie,
            parent, false
        )

        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.title.text = movieList.get(position).title
//        holder.imageView.setImageResource(movieList.get(position).backdropPath)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
    }

    fun setMovieList(movies: ArrayList<Movie>) {
        movieList = movies
    }
}