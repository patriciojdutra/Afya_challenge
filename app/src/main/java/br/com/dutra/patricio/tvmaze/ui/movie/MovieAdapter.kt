package br.com.dutra.patricio.tvmaze.ui.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.dutra.patricio.tvmaze.R
import br.com.dutra.patricio.tvmaze.extensions.load
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.ui.moviedetails.MovieDetailsActivity
import com.example.botacontra.banco.DataBase

class MovieAdapter constructor(val contex: Context, var list: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent,false)
        return MovieHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        val item = list[position]

        holder.movie_image.load(
            item.image.medium,
                contex)

        holder.txtTitleMovie.text = item.name

        if(item.isFavorite)
            holder.movie_favorite.setImageResource(android.R.drawable.star_big_on)
        else
            holder.movie_favorite.setImageResource(android.R.drawable.star_big_off)

        holder.movie_image.setOnClickListener {

            val intent = Intent(contex, MovieDetailsActivity::class.java)
            intent.putExtra("movie", item)
            contex.startActivity(intent)

        }

        holder.container_image_favorite.setOnClickListener {
            item.isFavorite = !item.isFavorite
            if(item.isFavorite) {

                //item.image.id = item.id
                //item.schedule?.id = item.id

                DataBase.getInstancia(contex).insert(item)
                //DataBase.getInstancia(contex).insert(item.image)
                holder.movie_favorite.setImageResource(android.R.drawable.star_big_on)
            } else {
                DataBase.getInstancia(contex).delete(item)
                //DataBase.getInstancia(contex).delete(item.image)
                holder.movie_favorite.setImageResource(android.R.drawable.star_big_off)
            }
        }
    }

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v){
        val movie_image = v.findViewById<ImageView>(R.id.movie_image)
        val movie_favorite = v.findViewById<ImageView>(R.id.favorite_image)
        val container_image_favorite = v.findViewById<LinearLayout>(R.id.container_image_favorite)
        val txtTitleMovie = v.findViewById<TextView>(R.id.txtTitleMovie)
    }

}
