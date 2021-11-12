package com.playground.spotipret.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.playground.spotipret.model.Playlist
import com.playground.spotipret.repository.PlaylistRepository
import com.playground.spotipret.ui.playlist.MainViewModel
import com.playground.spotipret.utils.BaseUnitTest
import com.playground.spotipret.utils.getValueForTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class MainViewModelShould:BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlistMock = mock<List<Playlist>>()
    private val expectedPlaylist = Result.success(playlistMock)


    @ExperimentalCoroutinesApi
    private fun mockSuccessfulCase(): MainViewModel {
        runBlockingTest {
            whenever(repository.getPlaylistsFromNetwork()).thenReturn(
                flow {
                    emit(expectedPlaylist)
                }
            )
        }
        return MainViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getPlaylistFromRepo() = runBlockingTest{
        val viewModel = mockSuccessfulCase()
        viewModel.requestPlaylist()
        verify(repository, times(1)).getPlaylistsFromNetwork()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnPlaylistFromRepository(){
        val viewModel = mockSuccessfulCase()
        viewModel.requestPlaylist()
        assertEquals(expectedPlaylist,viewModel.getPlaylist().getValueForTest())
    }
}