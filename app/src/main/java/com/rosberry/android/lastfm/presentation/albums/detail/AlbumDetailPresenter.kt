/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.detail

import com.arellomobile.mvp.InjectViewState
import com.rosberry.android.lastfm.base.presentation.AppPresenter
import com.rosberry.android.lastfm.domain.albums.AlbumsInteractor
import com.rosberry.android.lastfm.entity.Track
import kotlinx.coroutines.launch

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@InjectViewState
class AlbumDetailPresenter(
        private val albumsInteractor: AlbumsInteractor,
        private val albumName: String,
        private val artistName: String
) : AppPresenter<AlbumDetailView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        uiScope.launch {
            viewState.showLoading()
            try {
                val album = albumsInteractor.getDetailAlbum(albumName, artistName)
                    .await()
                viewState.showCover(album.cover)
                viewState.showAlbumName(album.name)
                viewState.showArtistName(album.artistName)
                viewState.showTracks(album.tracks.convertToTrackItem())
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showError(e.localizedMessage)
            }
        }
    }
}

private fun List<Track>.convertToTrackItem(): List<TrackItem> {
    return this.asSequence()
        .map { TrackItem(it) }
        .toList()
}