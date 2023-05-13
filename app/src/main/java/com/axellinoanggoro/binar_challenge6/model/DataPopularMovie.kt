package com.axellinoanggoro.binar_challenge6.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataPopularMovie(
    var image : String,
    var title : String,
    var date : String,
    var desc : String
) : Parcelable
