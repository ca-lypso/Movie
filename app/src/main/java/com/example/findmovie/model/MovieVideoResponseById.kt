package com.example.findmovie.model

import com.google.gson.annotations.SerializedName

data class MovieVideoResponseById(
    val id: Int,
    @SerializedName("results")
    val results: List<Video>
)