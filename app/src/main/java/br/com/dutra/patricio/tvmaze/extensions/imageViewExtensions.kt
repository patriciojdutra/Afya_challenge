package br.com.dutra.patricio.tvmaze.extensions

import android.content.Context
import android.widget.ImageView
import br.com.dutra.patricio.tvmaze.R
import com.bumptech.glide.Glide

fun ImageView.load(url:String, context:Context){
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.afya_image)
        .error(R.drawable.image_not_found_2)
        .into(this)
}