package br.com.dutra.patricio.tvmaze.viewmodel

import android.app.DownloadManager
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dutra.patricio.tvmaze.data.repository.MovieRepository
import br.com.dutra.patricio.tvmaze.extensions.addAllValue
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.model.SearchMovie
import com.example.botacontra.banco.DataBase
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel(var movieRepository: MovieRepository) : ViewModel() {

    var compositeDisposable = CompositeDisposable()
    var movieList = MutableLiveData<ArrayList<Movie>>()
    var loading = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()
    var pageSelected = 0
    var isFilter = false

    fun getMovies(){
        compositeDisposable.add(movieRepository.getMovieList(pageSelected)
            .doOnSubscribe {
                pageSelected++
                loading.value = true
            }.subscribe ({
                var filterList = it.filter { movie -> movie.name.isNotEmpty() }
                if(isFilter){
                    isFilter = false
                    movieList.value = it as ArrayList<Movie>
                }else
                    movieList.addAllValue(filterList)
                loading.value = false
            },{
                loading.value = false
                if(it.message != null)
                    errorMessage.value = it.message
            })
        )
    }

    fun getSearchedMovieList(query: String){

        compositeDisposable.add(movieRepository.getSearchedMovieList(query)
                .doOnSubscribe {
                    searchMovieEnable()
                }.subscribe ({
                    movieList.value = listFilter(it)
                    loading.value = false
                },{
                    loading.value = false
                    if(it.message != null)
                        errorMessage.value = it.message
                })
        )
    }

    fun getMovieListFavorite(context: Context){
        movieList.value = DataBase.getInstancia(context).getMovieFavorite() as ArrayList<Movie>
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun listFilter(listSearchMovie: List<SearchMovie>): ArrayList<Movie>{
        var list = ArrayList<Movie>()
        listSearchMovie.forEach {
            if(it.movie.name.isNotEmpty())
                list.add(it.movie)
        }
        return list
    }

    private fun searchMovieEnable(){
        pageSelected = 0
        isFilter = true
        loading.value = true
    }

}