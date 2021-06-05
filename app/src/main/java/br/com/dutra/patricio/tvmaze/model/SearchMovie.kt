package br.com.dutra.patricio.tvmaze.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SearchMovie(
    @SerializedName("score") var score : Double,
    @SerializedName("show") var movie : Movie,
)