package br.com.dutra.patricio.tvmaze.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.dutra.patricio.tvmaze.R
import br.com.dutra.patricio.tvmaze.databinding.ActivityMovieListBinding
import br.com.dutra.patricio.tvmaze.model.Show
import br.com.dutra.patricio.tvmaze.viewmodel.MovieViewModel

class MovieListActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()
    private val binding by lazy{ ActivityMovieListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        observes()
        viewModel.getMovies(1)

    }

    private fun observes() {
        viewModel.movie_list.observe(this, Observer {
            loadMovies(it)
        })
    }

    private fun setToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        toolbar.setSubtitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        setTitle(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_movie -> {
                //AllShowsActivity.start(this)
                true
            }
            R.id.action_favorites -> {
                //FavoriteShowsActivity.start(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun loadMovies(list : ArrayList<Show>){
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(this,2)
        binding.recyclerViewMovies.adapter = MovieAdapter(this, list)
    }

}