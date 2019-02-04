/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.detail

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.rosberry.android.lastfm.entity.Track

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@InjectViewState
class AlbumDetailPresenter : MvpPresenter<AlbumDetailView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showAlbumName("Album")
        viewState.showArtistName("Artist")
        viewState.showTracks(
                listOf(
                        TrackItem(Track("first")),
                        TrackItem(Track("second")),
                        TrackItem(Track("third"))
                )
        )
    }
}