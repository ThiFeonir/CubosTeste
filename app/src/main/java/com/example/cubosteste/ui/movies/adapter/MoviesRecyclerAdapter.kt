package com.example.cubosteste.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cubosteste.R
import com.example.cubosteste.data.model.movies.MovieResponse

class MoviesRecyclerAdapter(
    private val callback: (MovieResponse) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<MovieResponse> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movies_list, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], callback)
    }

    fun setList(list: List<MovieResponse>) {
        movies = list
    }
}