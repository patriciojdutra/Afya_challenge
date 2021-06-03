package br.com.dutra.patricio.tvmaze.data.service


import br.com.dutra.patricio.tvmaze.data.remote.Endpoint
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class Retrofit {

    companion object {
        val URLSERVER = "https://api.tvmaze.com/"
    }

    fun getRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(URLSERVER)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val endpoint: Endpoint = getRetrofitInstance().create(Endpoint::class.java)
}