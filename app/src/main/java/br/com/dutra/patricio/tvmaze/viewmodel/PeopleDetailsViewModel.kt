package br.com.dutra.patricio.tvmaze.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dutra.patricio.tvmaze.data.repository.MovieRepository
import br.com.dutra.patricio.tvmaze.model.Episode
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.model.Participations
import br.com.dutra.patricio.tvmaze.util.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

class PeopleDetailsViewModel(var movieRepository: MovieRepository) : BaseViewModel() {

    var movieList = MutableLiveData<ArrayList<Movie>>()

    fun getParticipations(id:Int){
        compositeDisposable.add(movieRepository.getParticipations(id)
            .subscribe ({
                val list = ArrayList<Movie>()
                it.forEach { participations ->
                    list.add(participations.Embedded.show)
                }
                movieList.value = list
            },{
                if(it.message != null)
                    errorMessage.value = it.message
            })
        )
    }
}