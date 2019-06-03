package com.example.cubosteste.ui.movie_detail

import android.graphics.Bitmap
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.cubosteste.R
import com.example.cubosteste.app.utils.loadImage
import kotlinx.android.synthetic.main.activity_movie_detail.*

class ActivityMovieDetail : AppCompatActivity() {

    companion object {
        const val IMAGE_PATH = "image-path"
        const val MOVIE_DESCRIPTION = "movie-description"
        const val MOVIE_TITLE = "movie-title"
    }


    lateinit var description: String
    lateinit var pathImage: String
    lateinit var movieTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        description = intent.getStringExtra(MOVIE_DESCRIPTION)
        pathImage = intent.getStringExtra(IMAGE_PATH)
        movieTitle = intent.getStringExtra(MOVIE_TITLE)

        setupToolbar(movieTitle)

        setUpLayout(pathImage, description)
    }

    private fun setMovieImage(url: String) {
        imgvMovie.loadImage(url)
    }

    private fun setUpLayout(path: String, description: String) {
        tvMovieDescription.text = if (!description.isEmpty()) description else "Descrição não disponível"
        tvMovieDescription.movementMethod = ScrollingMovementMethod()
        setMovieImage(path)
    }

    private fun setupToolbar(title: String) {
        setSupportActionBar(toolbar)
        val bar = supportActionBar
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true)
            bar.title = title
        }
    }

}