package com.playground.spotipret.repository

import com.playground.spotipret.model.Playlist
import kotlinx.coroutines.flow.Flow


class PlaylistRepository {
    suspend fun getPlaylistsFromNetwork():Flow<Result<List<Playlist>>> {
        TODO("IMPLEMENTATION")
    }
}