package br.com.dutra.patricio.tvmaze.ui.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.dutra.patricio.tvmaze.R
import br.com.dutra.patricio.tvmaze.extensions.favorite
import br.com.dutra.patricio.tvmaze.extensions.load
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.ui.moviedetails.MovieDetailsActivity
import br.com.dutra.patricio.tvmaze.util.Constants
import com.example.botacontra.banco.DataBase

class MovieAdapter constructor(val context: Context, var list: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent,false)
        return MovieHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        var item = DataBase.getInstancia(context).checkIfIsFavorite(list[position].id, list[position].name)

        if(item == null) item = list[position]
        holder.movie_image.load(item.image?.medium?:"", context)
        holder.txtTitleMovie.text = item.name
        holder.movie_favorite.favorite(item.isFavorite, context)

        holder.movie_image.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(Constants.keyMovie, item)
            context.startActivity(intent)
        }

        holder.container_image_favorite.setOnClickListener {
            item.isFavorite = !item.isFavorite
            holder.movie_favorite.favorite(item.isFavorite, context)
            if(item.isFavorite) DataBase.getInstancia(context).insert(item)
            else DataBase.getInstancia(context).delete(item)
        }
    }

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v){
        val movie_image = v.findViewById<ImageView>(R.id.movie_image)
        val movie_favorite = v.findViewById<ImageView>(R.id.favorite_image)
        val container_image_favorite = v.findViewById<LinearLayout>(R.id.container_image_favorite)
        val txtTitleMovie = v.findViewById<TextView>(R.id.txtTitleMovie)
    }

}
