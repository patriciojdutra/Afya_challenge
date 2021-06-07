package br.com.dutra.patricio.tvmaze.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

class Image : Serializable {
    var medium = ""
    var original: String? = ""
}