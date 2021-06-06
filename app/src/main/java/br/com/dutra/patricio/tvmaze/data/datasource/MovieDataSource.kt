package br.com.dutra.patricio.tvmaze.data.datasource

import br.com.dutra.patricio.tvmaze.model.Episode
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.model.SearchMovie
import io.reactivex.Observable

interface MovieDataSource {

    fun getMovieList(page: Int) : Observable<List<Movie>>

    fun getSearchedMovieList(query:String) : Observable<List<SearchMovie>>

    fun getEpisodes(id: Int) : Observable<List<Episode>>


}