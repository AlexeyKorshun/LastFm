/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.detail

import com.arellomobile.mvp.InjectViewState
import com.rosberry.android.lastfm.base.presentation.AppPresenter
import com.rosberry.android.lastfm.domain.albums.AlbumsInteractor
import com.rosberry.android.lastfm.entity.DetailAlbum
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

    private var isFavorite: Boolean = false
    private lateinit var album: DetailAlbum

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        uiScope.launch {
            viewState.showLoading()
            try {
                album = albumsInteractor.getDetailAlbum(albumName, artistName)
                    .await()
                isFavorite = albumsInteractor.isFavoriteAlbum(artistName, albumName)
                    .await()
                viewState.showCover(album.cover)
                viewState.showAlbumName(album.name)
                viewState.showArtistName(album.artistName)
                viewState.showTracks(album.tracks.convertToTrackItem())
                viewState.showIsFavorite(isFavorite)
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showError(e.localizedMessage)
            }
        }
    }

    fun clickFavorite() {
        if (isFavorite) albumsInteractor.removeAlbumFromFavorites(album)
        else albumsInteractor.addAlbumToFavorites(album)
        isFavorite = !isFavorite
        viewState.showIsFavorite(isFavorite)

    }
}

private fun List<Track>.convertToTrackItem(): List<TrackItem> {
    return this.asSequence()
        .map { TrackItem(it) }
        .toList()
}