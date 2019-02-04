/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.detail

import com.arellomobile.mvp.MvpView

/**
 * @author Alexei Korshun on 03/02/2019.
 */
interface AlbumDetailView : MvpView {

    fun showLoading()
    fun showError(message: String)
    fun showTracks(tracks: List<TrackItem>)
    fun showAlbumName(name: String)
    fun showArtistName(name: String)
    fun showCover(url: String)
}