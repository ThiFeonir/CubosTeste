package com.example.cubosteste.app.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.cubosteste.R

fun ImageView.loadImage(url: String) {
    Glide
        .with(this.context)
        .load(Constants.BASE_URL_IMG + url)
        .centerCrop()
        .placeholder(R.mipmap.ic_launcher_round)
        .into(this)
}