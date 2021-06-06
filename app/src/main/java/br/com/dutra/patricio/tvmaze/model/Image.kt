package br.com.dutra.patricio.tvmaze.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

class Image : Serializable {
//    @PrimaryKey
//    var id = 0
    var medium = ""
    var original = ""
}