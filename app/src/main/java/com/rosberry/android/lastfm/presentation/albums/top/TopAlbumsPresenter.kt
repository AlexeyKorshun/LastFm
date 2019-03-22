/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.top

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import com.rosberry.android.lastfm.Screens
import com.rosberry.android.lastfm.base.router.AppRouter
import com.rosberry.android.lastfm.domain.albums.AlbumsInteractor
import com.rosberry.android.lastfm.entity.Album
import de.appsfactory.mvplib.annotations.MVPIncludeToState
import de.appsfactory.mvplib.presenter.MVPPresenter

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class TopAlbumsPresenter(
        private val router: AppRouter,
        private val albumsInteractor: AlbumsInteractor,
        private val artistName: String
) : MVPPresenter() {

    @MVPIncludeToState
    val isLoading = ObservableBoolean(false)

    @MVPIncludeToState
    val topAlbums = ObservableArrayList<TopAlbumPresenter>()

    @MVPIncludeToState
    val isError = ObservableBoolean(false)

    override fun onStart() {
        super.onStart()
        if (!isRestored) {
            loadData()
        }
    }

    fun click(item: Album) {
        router.navigateTo(Screens.AlbumDetailScreen(artistName, item.name))
    }

    private fun loadData() {
        isLoading.set(true)
        doInBackground(LOADER_TOP_ALBUM_ID) { albumsInteractor.getTopAlbums(artistName) }
            .addOnSuccess { items ->
                topAlbums.clear()
                topAlbums.addAll(items.convertToItems())
                isLoading.set(false)
            }
            .addOnError {
                isLoading.set(false)
                isError.set(true)
            }
            .execute()
    }
}

private fun List<Album>.convertToItems(): List<TopAlbumPresenter> {
    return this.asSequence()
        .map { TopAlbumPresenter(it) }
        .toList()
}

private const val LOADER_TOP_ALBUM_ID = 322