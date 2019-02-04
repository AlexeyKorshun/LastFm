/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.main.favorites

import com.arellomobile.mvp.InjectViewState
import com.rosberry.android.lastfm.Screens
import com.rosberry.android.lastfm.base.presentation.AppPresenter
import com.rosberry.android.lastfm.base.router.AppRouter
import com.rosberry.android.lastfm.domain.albums.AlbumsInteractor
import com.rosberry.android.lastfm.entity.DetailAlbum
import kotlinx.coroutines.launch

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@InjectViewState
class FavoritesPresenter(
        private val router: AppRouter,
        private val albumsInteractor: AlbumsInteractor
) : AppPresenter<FavoritesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        uiScope.launch {
            try {
                val albums = albumsInteractor.getFavoritesAlbums()
                    .await()
                viewState.showFavorites(albums.convertToItems())
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showError(e.localizedMessage)
            }
        }
    }

    fun click(item: DetailAlbum) {
        router.navigateTo(Screens.AlbumDetailScreen(item.artistName, item.name))
    }
}

private fun List<DetailAlbum>.convertToItems(): List<FavoriteAlbumItem> {
    return this.asSequence()
        .map { FavoriteAlbumItem(it) }
        .toList()
}