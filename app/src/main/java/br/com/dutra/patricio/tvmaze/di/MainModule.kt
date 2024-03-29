package br.com.dutra.patricio.tvmaze.di

import br.com.dutra.patricio.tvmaze.data.datasource.MovieDataSource
import br.com.dutra.patricio.tvmaze.data.datasource.MovieDataSourceImpl
import br.com.dutra.patricio.tvmaze.data.repository.MovieRepository
import br.com.dutra.patricio.tvmaze.data.repository.MovieRepositoryImpl
import br.com.dutra.patricio.tvmaze.viewmodel.MovieDetailsViewModel
import br.com.dutra.patricio.tvmaze.viewmodel.MovieViewModel
import br.com.dutra.patricio.tvmaze.viewmodel.PeopleDetailsViewModel
import br.com.dutra.patricio.tvmaze.viewmodel.PeopleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    factory {
        val movieDataSource:MovieDataSource = MovieDataSourceImpl()
        val movieRepository: MovieRepository = MovieRepositoryImpl(movieDataSource)
        return@factory movieRepository
    }

    viewModel {
        MovieViewModel(
            movieRepository = get()
        )
    }

    viewModel {
        MovieDetailsViewModel(
            movieRepository = get()
        )
    }

    viewModel {
        PeopleViewModel(
            movieRepository = get()
        )
    }

    viewModel {
        PeopleDetailsViewModel(
                movieRepository = get()
        )
    }
}