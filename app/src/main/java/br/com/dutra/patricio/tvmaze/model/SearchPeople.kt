package br.com.dutra.patricio.tvmaze.model

import com.google.gson.annotations.SerializedName

data class SearchPeople(
    @SerializedName("score") var score : Double,
    @SerializedName("person") var person : Person,
)