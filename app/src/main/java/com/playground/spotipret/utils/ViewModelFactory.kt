package com.playground.spotipret.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.playground.spotipret.repository.PlaylistRepository
import com.playground.spotipret.ui.playlist.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class ViewModelFactory @Inject constructor(
    private val repo: PlaylistRepository
):ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}