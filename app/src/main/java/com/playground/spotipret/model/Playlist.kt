package com.playground.spotipret.model

import com.google.gson.annotations.SerializedName

data class Playlist (
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("category") val category:String
)