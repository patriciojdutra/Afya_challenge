package br.com.dutra.patricio.tvmaze.data.service


import br.com.dutra.patricio.tvmaze.data.remote.Endpoint
import br.com.dutra.patricio.tvmaze.util.Constants
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class Retrofit {

    fun getRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URLSERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val endpoint: Endpoint = getRetrofitInstance().create(Endpoint::class.java)
}