package br.com.dutra.patricio.tvmaze.data.repository

import br.com.dutra.patricio.tvmaze.data.datasource.MovieDataSource
import br.com.dutra.patricio.tvmaze.model.Episode
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.model.SearchMovie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl(private var movieDataSource: MovieDataSource) : MovieRepository {

    override fun getMovieList(page: Int): Observable<List<Movie>> {
        return movieDataSource.getMovieList(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun getSearchedMovieList(query: String): Observable<List<SearchMovie>> {
        return movieDataSource.getSearchedMovieList(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    override fun getEpisodes(id: Int): Observable<List<Episode>> {
        return movieDataSource.getEpisodes(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}