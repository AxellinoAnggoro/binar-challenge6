package com.axellinoanggoro.binar_challenge6.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class DataFavMovie(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var date : String,
    var desc : String,
    var image : String,
    ) : Parcelable
