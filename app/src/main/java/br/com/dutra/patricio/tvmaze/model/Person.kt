package br.com.dutra.patricio.tvmaze.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Person (
    @SerializedName("id") var id : Int,
    @SerializedName("url") var url : String,
    @SerializedName("name") var name : String,
    @SerializedName("country") var country : Country,
    @SerializedName("birthday") var birthday : String,
    @SerializedName("deathday") var deathday : String,
    @SerializedName("gender") var gender : String,
    @SerializedName("image") var image : Image?,
    //@SerializedName("_links") var Links : Links
): Serializable