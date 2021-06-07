package com.example.botacontra.banco


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.dutra.patricio.tvmaze.model.Genres
import br.com.dutra.patricio.tvmaze.model.Image
import br.com.dutra.patricio.tvmaze.model.Movie
import br.com.dutra.patricio.tvmaze.model.Schedule
import br.com.dutra.patricio.tvmaze.util.Converters

@TypeConverters(Converters::class)
@Database(entities = arrayOf(Movie::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun baseDao(): FavoriteDao
}