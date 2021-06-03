package br.com.dutra.patricio.tvmaze.data.repository

import br.com.dutra.patricio.tvmaze.model.Movie
import io.reactivex.Observable

interface MovieRepository {

    fun getMovieList(page: Int) : Observable<List<Movie>>

}