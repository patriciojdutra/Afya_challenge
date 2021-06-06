package br.com.dutra.patricio.tvmaze.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Movie : Serializable {

    @PrimaryKey
    var id = 0
    var name = ""
    var genres: List<String> = arrayListOf()
    @Embedded
    var schedule = Schedule()
    @Embedded
    var image = Image()
    var summary = ""
    var isFavorite = false

}



