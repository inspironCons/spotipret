package com.playground.spotipret.ui.playlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playground.spotipret.model.Playlist
import com.playground.spotipret.repository.PlaylistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo:PlaylistRepository
) :ViewModel() {
    private val playlists = MutableLiveData<Result<List<Playlist>>>()
    private val loader = MutableLiveData<Boolean>()

    fun getPlaylist(): LiveData<Result<List<Playlist>>> = playlists

    fun requestPlaylist() = viewModelScope.launch{
        loader.postValue(true)
        repo.getPlaylistsFromNetwork()
            .collect { result->
                Log.d(MainViewModel::class.java.simpleName,"$result")
                playlists.value = result
                loader.postValue(false)
            }
    }

    fun spinnerShow():LiveData<Boolean> = loader
}