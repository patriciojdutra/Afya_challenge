package br.com.dutra.patricio.tvmaze.viewmodel

import android.app.DownloadManager
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dutra.patricio.tvmaze.data.repository.MovieRepository
import br.com.dutra.patricio.tvmaze.extensions.addAllValue
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.model.Person
import br.com.dutra.patricio.tvmaze.model.SearchMovie
import br.com.dutra.patricio.tvmaze.model.SearchPeople
import br.com.dutra.patricio.tvmaze.util.BaseViewModel
import com.example.botacontra.banco.DataBase
import io.reactivex.disposables.CompositeDisposable

class PeopleViewModel(var movieRepository: MovieRepository) : BaseViewModel() {

    var peopleList = MutableLiveData<ArrayList<Person>>()

    fun getSearchedPersonList(query: String){
        compositeDisposable.add(movieRepository.getSearchedPeopleList(query)
                .doOnSubscribe {
                    loading.value = true
                }.subscribe ({
                    peopleList.value = listFilter(it)
                    loading.value = false
                },{
                    loading.value = false
                    if(it.message != null)
                        errorMessage.value = it.message
                })
        )
    }

    private fun listFilter(listSearchPeople: List<SearchPeople>): ArrayList<Person>{
        val list = ArrayList<Person>()
        listSearchPeople.forEach {
            if(it.person.name.isNotEmpty())
                list.add(it.person)
        }
        return list
    }
}