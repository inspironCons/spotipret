package com.playground.spotipret.ui.playlist

import com.playground.spotipret.common.ServicesBuilder
import com.playground.spotipret.network.playlist.IPlaylist
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class PlaylistModule {

    @Provides
    fun iPlaylist():IPlaylist = ServicesBuilder.builderService(IPlaylist::class.java)
}