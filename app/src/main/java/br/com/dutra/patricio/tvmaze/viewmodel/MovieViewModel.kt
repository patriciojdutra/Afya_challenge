package br.com.dutra.patricio.tvmaze.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dutra.patricio.tvmaze.data.service.Retrofit
import br.com.dutra.patricio.tvmaze.model.Show
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieViewModel: ViewModel() {

    var movie_list = MutableLiveData<ArrayList<Show>>()

    fun getMovies(page: Int){

        val cal = Retrofit().endpoint.getShows(page)

        cal.enqueue(object : Callback<List<Show>> {

            override fun onResponse(call: Call<List<Show>>, response: Response<List<Show>>) {

                if (response.isSuccessful && response.body() != null) {
                    movie_list.value = response.body() as ArrayList<Show>
                } else {
                    "//resposta.sucesso(NoticiasRetorno(response.errorBody()?.string().toString()))"
                }
            }

            override fun onFailure(result: Call<List<Show>>, t: Throwable) {
                "//resposta.sucesso(NoticiasRetorno(t.message.toString()))"
            }
        })
    }

}