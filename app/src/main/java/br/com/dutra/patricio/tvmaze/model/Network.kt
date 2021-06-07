package br.com.dutra.patricio.tvmaze.model

import com.google.gson.annotations.SerializedName

   
data class Network (
   @SerializedName("id") var id : Int,
   @SerializedName("name") var name : String,
   @SerializedName("country") var country : Country
)