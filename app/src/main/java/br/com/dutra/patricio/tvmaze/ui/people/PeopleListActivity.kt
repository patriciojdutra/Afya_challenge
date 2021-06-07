package br.com.dutra.patricio.tvmaze.ui.people

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.dutra.patricio.tvmaze.databinding.ActivityPeopleListBinding
import br.com.dutra.patricio.tvmaze.extensions.setVisible
import br.com.dutra.patricio.tvmaze.model.Person
import br.com.dutra.patricio.tvmaze.util.Alert
import br.com.dutra.patricio.tvmaze.util.BaseActivity
import br.com.dutra.patricio.tvmaze.viewmodel.PeopleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PeopleListActivity : BaseActivity() {

    private val viewModel: PeopleViewModel by viewModel()
    private val binding by lazy{ ActivityPeopleListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        observes()
        init(savedInstanceState)
        setupSearchView()
    }

    override fun init(savedInstanceState: Bundle?){
        if(savedInstanceState != null)
            loadPeople(viewModel.peopleList.value!!)
    }

    private fun setupSearchView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty()) {
                    viewModel.getSearchedPersonList(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isEmpty()) {
                    loadPeople(ArrayList())
                }
                return false
            }
        })

        binding.searchView.setOnCloseListener {
            loadPeople(ArrayList())
            false
        }
    }

    override fun observes() {
        viewModel.peopleList.observe(this, {
            loadPeople(list = it)
        })

        viewModel.loading.observe(this, {
            binding.cardViewLoading.setVisible(it)
        })

        viewModel.errorMessage.observe(this, {
            Alert.aviso(it, this) { finishAffinity() }
        })
    }

    private fun loadPeople(list: ArrayList<Person>){
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewMovies.adapter = PeopleAdapter(this, list)
    }
}