package br.com.dutra.patricio.tvmaze.model

import com.google.gson.annotations.SerializedName

data class Self (
   @SerializedName("href") var href : String
)