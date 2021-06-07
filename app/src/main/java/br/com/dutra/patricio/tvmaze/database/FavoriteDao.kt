package com.example.botacontra.banco

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.dutra.patricio.tvmaze.model.Image
import br.com.dutra.patricio.tvmaze.model.Movie
import io.reactivex.Observable

@Dao
interface FavoriteDao {

    @Query("Select * from Movie")
    fun getMovieFavorite(): Observable<List<Movie>>

    @Query("Select * from Movie where id = :id and name = :name")
    fun checkIfIsFavorite(id:Int, name:String): Movie

    @Insert
    fun insertAll(lista: List<Movie>)

    @Insert
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)

}