package br.com.dutra.patricio.tvmaze.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dutra.patricio.tvmaze.R
import br.com.dutra.patricio.tvmaze.databinding.ActivityMovieListBinding
import br.com.dutra.patricio.tvmaze.extensions.setVisible
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.util.Alerta
import br.com.dutra.patricio.tvmaze.util.Constants
import br.com.dutra.patricio.tvmaze.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModel()
    private val binding by lazy{ ActivityMovieListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        observes()
        listScrollEnd()
        init(savedInstanceState)
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

    private fun init(savedInstanceState: Bundle?){
        if(savedInstanceState == null)
            viewModel.getMovies()
        else{
            loadMovies(true, viewModel.movieList.value!!)
        }
    }

    private fun setToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        toolbar.setSubtitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        setTitle(R.string.app_name)
    }

    private fun observes() {
        viewModel.movieList.observe(this, {
            loadMovies(list = it)
        })

        viewModel.loading.observe(this, {
            binding.progressBar.setVisible(it)
        })

        viewModel.errorMessage.observe(this, {
            Alerta.aviso(it,this) { finishAffinity() }
        })

    }

    private fun loadMovies(loadList:Boolean = false, list : ArrayList<Movie>){

        if(loadList || viewModel.pageSelected <= Constants.minimumNumberOfPagesToUpdateList) {
            binding.recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
            binding.recyclerViewMovies.adapter = MovieAdapter(this, list)
        }else
            binding.recyclerViewMovies.adapter?.notifyDataSetChanged()
    }

    private fun listScrollEnd(){
        binding.recyclerViewMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getMovies()
                }
            }
        })
    }
}