package br.com.dutra.patricio.tvmaze.model

import com.google.gson.annotations.SerializedName

   
data class Externals (
   @SerializedName("tvrage") var tvrage : Int,
   @SerializedName("thetvdb") var thetvdb : Int,
   @SerializedName("imdb") var imdb : String
)