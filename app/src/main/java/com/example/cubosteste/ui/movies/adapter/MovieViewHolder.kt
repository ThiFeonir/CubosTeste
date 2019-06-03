package com.example.cubosteste.ui.movies.adapter

import android.graphics.Movie
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cubosteste.R
import com.example.cubosteste.app.utils.Constants
import com.example.cubosteste.app.utils.loadImage
import com.example.cubosteste.data.model.movies.MovieResponse
import kotlinx.android.synthetic.main.item_movies_list.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    fun bind(content: MovieResponse, callback: (MovieResponse) -> Unit) {
        with(itemView) {
            tvMovieName.text = content.title
            launch {
                content.posterPath?.let {
                    //progress.toVisible()
                    imgvMovie.loadImage(it)
                    //progress.toGone()
                } ?: run {
                    imgvMovie.setImageResource(R.mipmap.ic_launcher_round)
                    //progress.toGone()
                }
            }
        }
        itemView.setOnClickListener { callback.invoke(content) }
    }
}