package com.playground.spotipret.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.playground.spotipret.model.Playlist
import com.playground.spotipret.network.playlist.IPlaylist
import com.playground.spotipret.repository.PlaylistRepository
import com.playground.spotipret.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaylistRepoShould:BaseUnitTest() {

    private val api:IPlaylist = mock()
    private lateinit var repository:PlaylistRepository
    private val mockPlaylist = mock<List<Playlist>>()
    private val mockThrow = "Testing Error Throw"


    @Test
    fun fetchPlaylistFromService() = runBlockingTest {
        repository = PlaylistRepository(api)

        repository.getPlaylistsFromNetwork().first()

        verify(api, times(1)).playlists()
    }

    @Test
    fun mapDataFromApiToFlowAndEmitsThem() = runBlockingTest {
        whenever(api.playlists()).thenReturn(mockPlaylist)
        repository = PlaylistRepository(api)
        repository.getPlaylistsFromNetwork()
        assertEquals(Result.success(mockPlaylist),repository.getPlaylistsFromNetwork().first())
    }

    @Test
    fun emitErrorResultWhenApiFailure() = runBlockingTest {
        whenever(api.playlists()).thenThrow(RuntimeException(mockThrow))
        repository = PlaylistRepository(api)
        repository.getPlaylistsFromNetwork()

        assertEquals(mockThrow,repository.getPlaylistsFromNetwork().first().exceptionOrNull()?.localizedMessage)
    }
}