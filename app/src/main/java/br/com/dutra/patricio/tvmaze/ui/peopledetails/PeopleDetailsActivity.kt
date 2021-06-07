package br.com.dutra.patricio.tvmaze.ui.peopledetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import br.com.dutra.patricio.tvmaze.databinding.ActivityPeopleDetailsBinding
import br.com.dutra.patricio.tvmaze.extensions.load
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.model.Person
import br.com.dutra.patricio.tvmaze.ui.movie.MovieAdapter
import br.com.dutra.patricio.tvmaze.util.Alert
import br.com.dutra.patricio.tvmaze.util.BaseActivity
import br.com.dutra.patricio.tvmaze.util.Constants
import br.com.dutra.patricio.tvmaze.viewmodel.PeopleDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class PeopleDetailsActivity : BaseActivity() {

    private val viewModel: PeopleDetailsViewModel by viewModel()
    private val binding by lazy{ ActivityPeopleDetailsBinding.inflate(layoutInflater) }
    private val person: Person by lazy {
        intent.getSerializableExtra(Constants.keyPeople) as Person
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        observes()
        init()
    }

    override fun setToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(person.name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun observes() {
        viewModel.movieList.observe(this, {
            loadParticipations(it)
        })

        viewModel.errorMessage.observe(this, {
            Alert.aviso(it, this) { finishAffinity() }
        })
    }

    private fun loadParticipations(list: ArrayList<Movie>) {
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewMovies.adapter = MovieAdapter(this, list)
    }

    override fun init(){
        viewModel.getParticipations(person.id)
        person.image?.let { binding.imgViewMovieDetails.load(it.original, this) }
    }
}