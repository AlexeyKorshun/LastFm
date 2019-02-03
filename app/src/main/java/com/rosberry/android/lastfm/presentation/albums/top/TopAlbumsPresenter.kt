/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.top

import com.arellomobile.mvp.InjectViewState
import com.rosberry.android.lastfm.base.presentation.AppPresenter
import com.rosberry.android.lastfm.base.router.AppRouter
import com.rosberry.android.lastfm.domain.albums.AlbumsInteractor
import com.rosberry.android.lastfm.entity.Album

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
        viewState.showLoading()
    }

    fun click(item: Album) {

    }
}