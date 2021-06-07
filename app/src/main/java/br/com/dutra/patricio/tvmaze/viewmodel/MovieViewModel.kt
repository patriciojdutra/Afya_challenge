package br.com.dutra.patricio.tvmaze.viewmodel

import android.app.DownloadManager
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dutra.patricio.tvmaze.data.repository.MovieRepository
import br.com.dutra.patricio.tvmaze.extensions.addAllValue
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.model.SearchMovie
import br.com.dutra.patricio.tvmaze.util.BaseViewModel
import com.example.botacontra.banco.DataBase
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel(var movieRepository: MovieRepository) : BaseViewModel() {

    var movieList = MutableLiveData<ArrayList<Movie>>()
    var movieListFavorite = MutableLiveData<ArrayList<Movie>>()
    var pageSelected = 0
    var isFilter = false

    fun getMovies(){
        compositeDisposable.add(movieRepository.getMovieList(pageSelected)
            .doOnSubscribe {
                pageSelected++
                loading.value = true
            }.subscribe ({
                val filterList = it.filter { movie -> movie.name.isNotEmpty() }
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
                    filterhMovieEnable()
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

    fun getFavoriteMovies(context: Context){
        compositeDisposable.add(movieRepository.getFavoriteMovies(context)
            .doOnSubscribe {
                filterhMovieEnable()
            }.subscribe ({
                    movieListFavorite.value = it as ArrayList<Movie>
                    loading.value = false
            },{
                loading.value = false
                if(it.message != null)
                    errorMessage.value = it.message
            })
        )
    }

    private fun listFilter(listSearchMovie: List<SearchMovie>): ArrayList<Movie>{
        val list = ArrayList<Movie>()
        listSearchMovie.forEach {
            if(it.movie.name.isNotEmpty())
                list.add(it.movie)
        }
        return list
    }

    private fun filterhMovieEnable(){
        pageSelected = 0
        isFilter = true
        loading.value = true
    }

}