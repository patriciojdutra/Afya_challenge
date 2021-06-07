package br.com.dutra.patricio.tvmaze.data.datasource

import android.content.Context
import br.com.dutra.patricio.tvmaze.data.service.Retrofit
import br.com.dutra.patricio.tvmaze.model.*
import com.example.botacontra.banco.DataBase
import io.reactivex.Observable

class MovieDataSourceImpl : MovieDataSource {

    override fun getMovieList(page: Int): Observable<List<Movie>> {
        return Retrofit().endpoint.getShows(page)
    }

    override fun getSearchedMovieList(query: String): Observable<List<SearchMovie>> {
        return Retrofit().endpoint.getSearchedMovieList(query)
    }

    override fun getEpisodes(id: Int): Observable<List<Episode>> {
        return Retrofit().endpoint.getEpisodes(id)
    }

    override fun getFavoriteMovies(context: Context): Observable<List<Movie>> {
        return DataBase.getInstancia(context).getMovieFavorite()
    }

    override fun getSearchedPeopleList(query: String): Observable<List<SearchPeople>> {
        return Retrofit().endpoint.getSearchedPeopleList(query)
    }

    override fun getParticipations(id: Int): Observable<List<Participations>> {
        return Retrofit().endpoint.getParticipations(id)
    }


}