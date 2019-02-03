/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm

import androidx.fragment.app.Fragment
import com.rosberry.android.lastfm.entity.Artist
import com.rosberry.android.lastfm.ui.albums.detail.AlbumDetailFragment
import com.rosberry.android.lastfm.ui.albums.top.TopAlbumsFragment
import com.rosberry.android.lastfm.ui.main.MainFragment
import com.rosberry.android.lastfm.ui.main.favorites.FavoritesFragment
import com.rosberry.android.lastfm.ui.main.search.SearchFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * @author Alexei Korshun on 03/02/2019.
 */
object Screens {

    class MainScreen : SupportAppScreen() {

        override fun getFragment(): Fragment = MainFragment()
    }

    class FavoritesScreen : SupportAppScreen() {

        override fun getFragment(): Fragment = FavoritesFragment()
    }

    class SearchScreen : SupportAppScreen() {

        override fun getFragment(): Fragment = SearchFragment()
    }

    class TopAlbumsScreen(private val artist: Artist) : SupportAppScreen() {

        override fun getFragment(): Fragment = TopAlbumsFragment.newInstance(artist)
    }

    class AlbumDetailScreen(private val artistName: String, private val albumName: String) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return AlbumDetailFragment.newInstance(artistName, albumName)
        }
    }
}