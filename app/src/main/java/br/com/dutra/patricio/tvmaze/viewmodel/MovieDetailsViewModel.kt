package br.com.dutra.patricio.tvmaze.viewmodel

import android.app.DownloadManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dutra.patricio.tvmaze.data.repository.MovieRepository
import br.com.dutra.patricio.tvmaze.extensions.addAllValue
import br.com.dutra.patricio.tvmaze.model.Episodes
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.model.SearchMovie
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsViewModel(var movieRepository: MovieRepository) : ViewModel() {

    var compositeDisposable = CompositeDisposable()
    var episodeList = MutableLiveData<ArrayList<Episodes>>()
    var errorMessage = MutableLiveData<String>()

    fun getEpisode(id:Int){
        compositeDisposable.add(movieRepository.getEpisodes(id)
            .subscribe ({
                episodeList.value = it as ArrayList<Episodes>
            },{
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