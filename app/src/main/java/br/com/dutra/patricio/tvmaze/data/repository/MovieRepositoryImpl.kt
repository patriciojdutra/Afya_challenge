package br.com.dutra.patricio.tvmaze.data.repository

import br.com.dutra.patricio.tvmaze.data.datasource.MovieDataSource
import br.com.dutra.patricio.tvmaze.model.Movie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl(private var movieDataSource: MovieDataSource) : MovieRepository {

    override fun getMovieList(page: Int): Observable<List<Movie>> {
        return movieDataSource.getMovieList(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }


}