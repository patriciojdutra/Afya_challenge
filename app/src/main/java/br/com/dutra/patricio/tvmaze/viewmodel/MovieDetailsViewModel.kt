package br.com.dutra.patricio.tvmaze.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dutra.patricio.tvmaze.data.repository.MovieRepository
import br.com.dutra.patricio.tvmaze.model.Episode
import br.com.dutra.patricio.tvmaze.util.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsViewModel(var movieRepository: MovieRepository) : BaseViewModel() {

    var episodeList = MutableLiveData<ArrayList<Episode>>()

    fun getEpisode(id:Int){
        compositeDisposable.add(movieRepository.getEpisodes(id)
            .subscribe ({
                episodeList.value = it as ArrayList<Episode>
            },{
                if(it.message != null)
                    errorMessage.value = it.message
            })
        )
    }
}