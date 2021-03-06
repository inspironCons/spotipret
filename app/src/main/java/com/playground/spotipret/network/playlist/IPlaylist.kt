package com.playground.spotipret.network.playlist

import com.playground.spotipret.model.Playlist
import retrofit2.http.GET

interface IPlaylist {

    @GET("playlists")
    suspend fun playlists():List<Playlist>

}