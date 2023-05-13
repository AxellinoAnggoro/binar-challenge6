package com.axellinoanggoro.binar_challenge6.model

import com.google.gson.annotations.SerializedName

data class GetPopularMovie(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ResultPopularMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
