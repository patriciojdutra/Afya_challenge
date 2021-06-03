package br.com.dutra.patricio.tvmaze.data.datasource

import br.com.dutra.patricio.tvmaze.data.service.Retrofit
import br.com.dutra.patricio.tvmaze.model.Movie
import io.reactivex.Observable

class MovieDataSourceImpl : MovieDataSource {

    override fun getMovieList(page: Int): Observable<List<Movie>> {
        return Retrofit().endpoint.getShows(page)
    }
}