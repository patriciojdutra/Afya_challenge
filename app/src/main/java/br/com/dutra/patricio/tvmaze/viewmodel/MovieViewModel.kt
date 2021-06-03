package br.com.dutra.patricio.tvmaze.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dutra.patricio.tvmaze.data.repository.MovieRepository
import br.com.dutra.patricio.tvmaze.extensions.addAllValue
import br.com.dutra.patricio.tvmaze.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel(var movieRepository: MovieRepository) : ViewModel() {

    var compositeDisposable = CompositeDisposable()
    var movieList = MutableLiveData<ArrayList<Movie>>()
    var loading = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()
    var pageSelected = 0

    fun getMovies(){
        compositeDisposable.add(movieRepository.getMovieList(pageSelected)
            .doOnSubscribe {
                pageSelected++
                if(pageSelected > 1)
                    loading.value = true
            }.subscribe ({
                movieList.addAllValue(it)
                loading.value = false
            },{
                loading.value = false
                if(it.message != null)
                    errorMessage.value = it.message
            })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}