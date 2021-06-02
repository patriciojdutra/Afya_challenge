package br.com.dutra.patricio.tvmaze.data.remote


import br.com.dutra.patricio.tvmaze.model.Show
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {

    @GET("/shows")
    fun getShows(@Query("page") pager: Int): Call<List<Show>>

}