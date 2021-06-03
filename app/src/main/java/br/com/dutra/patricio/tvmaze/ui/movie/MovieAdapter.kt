package br.com.dutra.patricio.tvmaze.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.dutra.patricio.tvmaze.R
import br.com.dutra.patricio.tvmaze.extensions.load
import br.com.dutra.patricio.tvmaze.model.Movie
import com.squareup.picasso.Picasso
import java.lang.Exception

class MovieAdapter constructor(val contex: Context, var list: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent,false)
        return MovieHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        val item = list[position]

        holder.movie_image.load(item.image.original)

        if(item.isFavorite)
            holder.movie_favorite.setImageResource(android.R.drawable.star_big_on)
        else
            holder.movie_favorite.setImageResource(android.R.drawable.star_big_off)


        holder.movie_image.setOnClickListener {}

        holder.container_image_favorite.setOnClickListener {
            item.isFavorite = !item.isFavorite
            if(item.isFavorite)
                holder.movie_favorite.setImageResource(android.R.drawable.star_big_on)
            else
                holder.movie_favorite.setImageResource(android.R.drawable.star_big_off)
        }
    }

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v){

        val movie_image = v.findViewById<ImageView>(R.id.movie_image)
        val movie_favorite = v.findViewById<ImageView>(R.id.favorite_image)
        val container_image_favorite = v.findViewById<LinearLayout>(R.id.container_image_favorite)

    }


}
