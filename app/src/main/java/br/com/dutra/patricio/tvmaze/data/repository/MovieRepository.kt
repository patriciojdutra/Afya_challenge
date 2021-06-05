package br.com.dutra.patricio.tvmaze.data.repository

import br.com.dutra.patricio.tvmaze.model.Episodes
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.model.SearchMovie
import io.reactivex.Observable

interface MovieRepository {

    fun getMovieList(page: Int) : Observable<List<Movie>>

    fun getSearchedMovieList(query:String) : Observable<List<SearchMovie>>

    fun getEpisodes(id: Int) : Observable<List<Episodes>>


}