package com.example.botacontra.banco

import android.app.Activity
import android.content.Context
import androidx.room.Room
import br.com.dutra.patricio.tvmaze.util.Constants

class DataBase {

    companion object {
        var favoriteDao: FavoriteDao? = null
        @JvmStatic fun getInstancia(context: Context): FavoriteDao{
            return favoriteDao?: getDao(context)
        }

        private fun getDao(context: Context): FavoriteDao{
            favoriteDao = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    Constants.dbName)
                    .allowMainThreadQueries()
                    .build()
                    .baseDao()
            return favoriteDao as FavoriteDao
        }
    }
}