package com.playground.spotipret.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playground.spotipret.model.Playlist
import com.playground.spotipret.repository.PlaylistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo:PlaylistRepository
) :ViewModel() {

    private val playlists = MutableLiveData<Result<List<Playlist>>>()

    fun getPlaylist(): LiveData<Result<List<Playlist>>> = playlists

    fun requestPlaylist() = viewModelScope.launch{
        repo.getPlaylistsFromNetwork()
            .collect { result->
                playlists.value = result
            }
    }
}