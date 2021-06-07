package br.com.dutra.patricio.tvmaze.model

import com.google.gson.annotations.SerializedName

   
data class Links (
   @SerializedName("self") var self : Self,
   @SerializedName("previousepisode") var previousepisode : Previousepisode
)