package com.playground.spotipret

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertDrawableDisplayedAtPosition
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertListItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.rule.BaristaRule
import com.playground.spotipret.ui.playlist.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

    @get:Rule
    var mActivityRule = BaristaRule.create(MainActivity::class.java)

    @Test
    fun displayScreenTitle(){
        assertDisplayed("Playlist")
    }

    @Test
    fun displayPlaylist(){
        Thread.sleep(4000)
        //item  count  10 list
        assertListItemCount(R.id.rv_playlist,10)
        //title name in position 0 was "Hard Rock Cafe
        assertDisplayedAtPosition(R.id.rv_playlist,0,R.id.tv_title_playlist,"Hard Rock Cafe")
        assertDisplayedAtPosition(R.id.rv_playlist,0,R.id.tv_category_playlist,"rock")
        assertDrawableDisplayedAtPosition(R.id.rv_playlist,0,R.id.iv_playlist,R.drawable.ic_playlist)

    }
}