package br.com.dutra.patricio.tvmaze.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

class Schedule : Serializable {
    var time = ""
    var days: List<String> = arrayListOf()
}


