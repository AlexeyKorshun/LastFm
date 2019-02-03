/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.top

import com.arellomobile.mvp.InjectViewState
import com.rosberry.android.lastfm.Screens
import com.rosberry.android.lastfm.base.presentation.AppPresenter
import com.rosberry.android.lastfm.base.router.AppRouter
import com.rosberry.android.lastfm.domain.albums.AlbumsInteractor
import com.rosberry.android.lastfm.entity.Album
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@InjectViewState
class TopAlbumsPresenter(
        private val router: AppRouter,
        private val albumsInteractor: AlbumsInteractor,
        private val artistName: String
) : AppPresenter<TopAlbumsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadAlbums()
    }

    fun click(item: Album) {
        router.navigateTo(Screens.AlbumDetailScreen(artistName, item.name))
    }

    private fun loadAlbums() {
        uiScope.launch {
            try {
                viewState.showLoading()
                val albums = withContext(bgScope.coroutineContext) {
                    albumsInteractor.getTopAlbums(artistName)
                }.await()

                val albumsItems = albums.asSequence()
                    .map { TopAlbumItem(it) }
                    .toList()
                viewState.showResult(albumsItems)
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showError(e.localizedMessage)
            }
        }
    }
}