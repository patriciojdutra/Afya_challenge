package br.com.dutra.patricio.tvmaze.model

import com.google.gson.annotations.SerializedName

   
data class Embedded (
   @SerializedName("show") var show : Movie
)