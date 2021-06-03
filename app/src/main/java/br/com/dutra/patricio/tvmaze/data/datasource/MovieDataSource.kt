package br.com.dutra.patricio.tvmaze.data.datasource

import br.com.dutra.patricio.tvmaze.model.Movie
import io.reactivex.Observable

interface MovieDataSource {

    fun getMovieList(page: Int) : Observable<List<Movie>>

}