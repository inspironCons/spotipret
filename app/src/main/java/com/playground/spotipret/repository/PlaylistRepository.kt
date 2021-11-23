package com.playground.spotipret.repository

import com.playground.spotipret.model.Playlist
import com.playground.spotipret.network.playlist.IPlaylist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PlaylistRepository @Inject constructor(
    private val service: IPlaylist
) {

    suspend fun getPlaylistsFromNetwork():Flow<Result<List<Playlist>>> {
        return flow {
            emit(Result.success(service.playlists()))
        }.catch { e->
            emit(Result.failure(e))
        }
    }
}