/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.top

import com.rosberry.android.lastfm.Screens
import com.rosberry.android.lastfm.base.router.AppRouter
import com.rosberry.android.lastfm.domain.albums.AlbumsInteractor
import com.rosberry.android.lastfm.entity.Album
import de.appsfactory.mvplib.presenter.MVPPresenter

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class TopAlbumsPresenter(
        private val router: AppRouter,
        private val albumsInteractor: AlbumsInteractor,
        private val artistName: String
) : MVPPresenter() {

    fun click(item: Album) {
        router.navigateTo(Screens.AlbumDetailScreen(artistName, item.name))
    }
}

private fun List<Album>.convertToItems(): List<TopAlbumItem> {
    return this.asSequence()
        .map { TopAlbumItem(it) }
        .toList()
}