package br.com.dutra.patricio.tvmaze.data.remote


import br.com.dutra.patricio.tvmaze.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.*

interface Endpoint {

    @GET("shows")
    fun getShows(@Query("page") pager: Int): Observable<List<Movie>>

    @GET("search/shows")
    fun getSearchedMovieList(@Query("q") query: String): Observable<List<SearchMovie>>

    @GET("shows/{id}/episodes")
    fun getEpisodes(@Path("id") id: Int): Observable<List<Episode>>

    @GET("search/people")
    fun getSearchedPeopleList(@Query("q") query: String): Observable<List<SearchPeople>>

    @GET("people/{id}/castcredits?embed=show")
    fun getParticipations(@Path("id") id: Int): Observable<List<Participations>>

}