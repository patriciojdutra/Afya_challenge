package br.com.dutra.patricio.tvmaze.model

import com.google.gson.annotations.SerializedName

   
data class Rating (
   @SerializedName("average") var average : String
)