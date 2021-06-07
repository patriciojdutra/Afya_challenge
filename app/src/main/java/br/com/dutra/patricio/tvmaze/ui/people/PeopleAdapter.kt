package br.com.dutra.patricio.tvmaze.ui.people

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.dutra.patricio.tvmaze.R
import br.com.dutra.patricio.tvmaze.extensions.load
import br.com.dutra.patricio.tvmaze.model.Person
import br.com.dutra.patricio.tvmaze.ui.peopledetails.PeopleDetailsActivity
import br.com.dutra.patricio.tvmaze.util.Constants

class PeopleAdapter constructor(val context: Context, var list: ArrayList<Person>) : RecyclerView.Adapter<PeopleAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.people_list_item, parent,false)
        return MovieHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item = list[position]

        item.image?.let { holder.movie_image.load(it.medium, context) }
        holder.personName.text = item.name

        holder.movie_image.setOnClickListener {
            val intent = Intent(context, PeopleDetailsActivity::class.java)
            intent.putExtra(Constants.keyPeople, item)
            context.startActivity(intent)
        }
    }

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v){
        val movie_image = v.findViewById<ImageView>(R.id.movie_image)
        val personName = v.findViewById<TextView>(R.id.txtNamePerson)
    }

}
