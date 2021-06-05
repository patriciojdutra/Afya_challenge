package br.com.dutra.patricio.tvmaze.data.datasource

import br.com.dutra.patricio.tvmaze.data.service.Retrofit
import br.com.dutra.patricio.tvmaze.model.Episodes
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.model.SearchMovie
import io.reactivex.Observable

class MovieDataSourceImpl : MovieDataSource {

    override fun getMovieList(page: Int): Observable<List<Movie>> {
        return Retrofit().endpoint.getShows(page)
    }

    override fun getSearchedMovieList(query: String): Observable<List<SearchMovie>> {
        return Retrofit().endpoint.getSearchedMovieList(query)
    }

    override fun getEpisodes(id: Int): Observable<List<Episodes>> {
        return Retrofit().endpoint.getEpisodes(id)
    }
}