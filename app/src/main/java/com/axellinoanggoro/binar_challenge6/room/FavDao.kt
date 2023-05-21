package com.axellinoanggoro.binar_challenge6.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDao {
    @Insert
    fun insertData(favData: DataFavMovie)

    @Query("SELECT * FROM DataFavMovie")
    fun getDataNote() : List<DataFavMovie>

    @Delete
    fun deleteFav(favData : DataFavMovie)
}