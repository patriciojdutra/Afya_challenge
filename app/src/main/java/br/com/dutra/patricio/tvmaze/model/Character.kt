package br.com.dutra.patricio.tvmaze.model

import com.google.gson.annotations.SerializedName

data class Character (
   @SerializedName("href") var href : String
)