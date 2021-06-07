package br.com.dutra.patricio.tvmaze.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.view.MenuItem
import android.widget.ImageView
import androidx.core.content.ContextCompat
import br.com.dutra.patricio.tvmaze.R
import com.bumptech.glide.Glide

fun ImageView.load(url: String?, context:Context){
    Glide.with(context)
        .load(url?:"")
        .placeholder(R.drawable.afya_image)
        .error(R.drawable.image_not_found_2)
        .into(this)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.favorite(value: Boolean, context:Context){
    if(value) {
        val drawable = context.getDrawable(R.drawable.ic_star_grey600_24dp)
        drawable?.setColorFilter(ContextCompat.getColor(context, R.color.yellow), PorterDuff.Mode.SRC_ATOP)
        this.setImageDrawable(drawable)
    }
    else {
        this.setImageResource(R.drawable.ic_star_outline_white_24dp)
    }
}

@SuppressLint("UseCompatLoadingForDrawables")
fun MenuItem.favorite(value: Boolean, context:Context){
    if(value) {
        val drawable = context.getDrawable(R.drawable.ic_star_grey600_24dp)
        drawable?.setColorFilter(ContextCompat.getColor(context, R.color.yellow), PorterDuff.Mode.SRC_ATOP)
        this.icon = drawable
    }
    else {
        this.setIcon(R.drawable.ic_star_outline_white_24dp)
    }
}