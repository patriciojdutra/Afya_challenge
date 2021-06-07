package br.com.dutra.patricio.tvmaze.model

import com.google.gson.annotations.SerializedName

   
data class Participations (
   @SerializedName("self") var self : Boolean,
   @SerializedName("voice") var voice : Boolean,
   @SerializedName("_links") var Links : Links,
   @SerializedName("_embedded") var Embedded : Embedded
)