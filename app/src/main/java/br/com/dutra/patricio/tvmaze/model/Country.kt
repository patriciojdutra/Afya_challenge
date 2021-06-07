package br.com.dutra.patricio.tvmaze.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Country (
        @SerializedName("name") var name : String,
        @SerializedName("code") var code : String,
        @SerializedName("timezone") var timezone : String
): Serializable