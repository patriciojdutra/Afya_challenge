package br.com.dutra.patricio.tvmaze.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dutra.patricio.tvmaze.R
import br.com.dutra.patricio.tvmaze.databinding.ActivityMovieListBinding
import br.com.dutra.patricio.tvmaze.extensions.favorite
import br.com.dutra.patricio.tvmaze.extensions.setVisible
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.ui.people.PeopleListActivity
import br.com.dutra.patricio.tvmaze.util.*
import br.com.dutra.patricio.tvmaze.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieListActivity : BaseActivity() {

    private val viewModel: MovieViewModel by viewModel()
    private val binding by lazy{ ActivityMovieListBinding.inflate(layoutInflater) }
    private val biometricUtil = BiometricUtil()
    private lateinit var menuItem: Menu
    var favoriteListIsEnable = false
    val spanCountGridLayout = 2
    val scrollDirection = 1

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        observes()
        listScrollEnd()
        init(savedInstanceState)
        setupSearchView()
        setupBiometric()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        menuItem = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorites -> {
                favoriteSelect()
                true
            }
            R.id.action_search_people -> {
                startActivity(Intent(this, PeopleListActivity::class.java))
                true
            }
            R.id.action_pin -> {
                biometricUtil.authenticate(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteSelect() {
        favoriteListIsEnable = !favoriteListIsEnable
        menuItem.findItem(R.id.action_favorites).favorite(favoriteListIsEnable,this)

        if(favoriteListIsEnable){
            viewModel.getFavoriteMovies(this)
            binding.searchView.setVisible(false)
        }
        else{
            binding.searchView.setVisible(true)
            if(viewModel.movieList.value.isNullOrEmpty())
                viewModel.getMovies()
            else
                viewModel.movieList.value?.let { loadMovies(true, it) }
        }
    }

    override fun init(savedInstanceState: Bundle?){
        when(savedInstanceState){
            null -> viewModel.getMovies()
            else -> viewModel.movieList.value?.let { loadMovies(true, it) }
        }
    }

    override fun setToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun setupBiometric(){
        biometricUtil.startBiometricPrompt(this){
            Toast.makeText(this,getString(R.string.authentication_was_successful), Toast.LENGTH_LONG).show()
            PreferencesUtil.putBoolean(this,Constants.authenticationIsEnable,true)
        }
    }

    private fun setupSearchView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty())
                    viewModel.getSearchedMovieList(query)
                return false
            }
            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })
        binding.searchView.setOnCloseListener {
            viewModel.getMovies()
            false
        }
    }

    override fun observes() {
        viewModel.movieList.observe(this, {
            loadMovies(list = it)
        })
        viewModel.movieListFavorite.observe(this, {
            if(favoriteListIsEnable)
                loadMovies(list = it)
        })
        viewModel.loading.observe(this, {
            binding.cardViewLoading.setVisible(it)
        })
        viewModel.errorMessage.observe(this, {
            Alert.aviso(it, this) { finishAffinity() }
        })
    }

    private fun loadMovies(loadList: Boolean = false, list: ArrayList<Movie>){
        if(loadList || viewModel.pageSelected <= Constants.minimumNumberOfPagesToUpdateList) {
            binding.recyclerViewMovies.layoutManager = GridLayoutManager(this, spanCountGridLayout)
            binding.recyclerViewMovies.adapter = MovieAdapter(this, list)
        }else
            binding.recyclerViewMovies.adapter?.notifyDataSetChanged()
    }

    private fun listScrollEnd(){
        binding.recyclerViewMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(scrollDirection) &&
                        newState == RecyclerView.SCROLL_STATE_IDLE &&
                        !viewModel.isFilter) {
                    viewModel.getMovies()
                }
            }
        })
    }
}