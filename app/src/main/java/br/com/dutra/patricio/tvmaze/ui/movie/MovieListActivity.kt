package br.com.dutra.patricio.tvmaze.ui.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dutra.patricio.tvmaze.R
import br.com.dutra.patricio.tvmaze.databinding.ActivityMovieListBinding
import br.com.dutra.patricio.tvmaze.extensions.setVisible
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.util.Alerta
import br.com.dutra.patricio.tvmaze.util.BiometricUtil
import br.com.dutra.patricio.tvmaze.util.Constants
import br.com.dutra.patricio.tvmaze.util.PreferencesUtil
import br.com.dutra.patricio.tvmaze.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieListActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModel()
    private val binding by lazy{ ActivityMovieListBinding.inflate(layoutInflater) }
    private val biometricUtil = BiometricUtil()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        observes()
        listScrollEnd()
        init(savedInstanceState)
        setupSearchView()
        biometricUtil.startBiometricPrompt(this){
            Toast.makeText(this,"Authentication was successful", Toast.LENGTH_LONG).show()
            PreferencesUtil.putBoolean(this,"Authentication_Enable",true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorites -> {
                viewModel.getMovieListFavorite(this)
                true
            }

            R.id.action_pin -> {
                biometricUtil.authenticate(this)
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
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupSearchView(){

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty()) {
                    viewModel.getSearchedMovieList(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isEmpty()) {
                    viewModel.getMovies()
                }
                return false
            }
        })

        binding.searchView.setOnCloseListener {
            viewModel.getMovies()
            false
        }
    }

    private fun observes() {
        viewModel.movieList.observe(this, {
            loadMovies(list = it)
        })

        viewModel.loading.observe(this, {
            binding.cardViewLoading.setVisible(it)
        })

        viewModel.errorMessage.observe(this, {
            Alerta.aviso(it, this) { finishAffinity() }
        })

    }

    private fun loadMovies(loadList: Boolean = false, list: ArrayList<Movie>){

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
                if (!recyclerView.canScrollVertically(1) &&
                        newState == RecyclerView.SCROLL_STATE_IDLE &&
                        !viewModel.isFilter) {
                    viewModel.getMovies()
                }
            }
        })
    }
}