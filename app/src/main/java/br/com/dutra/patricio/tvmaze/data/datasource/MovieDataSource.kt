package br.com.dutra.patricio.tvmaze.data.datasource

import android.content.Context
import br.com.dutra.patricio.tvmaze.model.*
import io.reactivex.Observable

interface MovieDataSource {

    fun getMovieList(page: Int) : Observable<List<Movie>>

    fun getSearchedMovieList(query:String) : Observable<List<SearchMovie>>

    fun getEpisodes(id: Int) : Observable<List<Episode>>

    fun getFavoriteMovies(context: Context) : Observable<List<Movie>>

    fun getSearchedPeopleList(query:String) : Observable<List<SearchPeople>>

    fun getParticipations(id: Int) : Observable<List<Participations>>

}