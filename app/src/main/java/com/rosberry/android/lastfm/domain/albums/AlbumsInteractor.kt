/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.domain.albums

import com.rosberry.android.lastfm.entity.Album
import com.rosberry.android.lastfm.entity.DetailAlbum
import kotlinx.coroutines.Deferred

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class AlbumsInteractor(
        private val albumsRepository: AlbumsRepository
) {

    fun getTopAlbums(artistName: String): Deferred<List<Album>> {
        return albumsRepository.getTopAlbums(artistName)
    }

    fun getDetailAlbum(albumName: String, artistName: String): Deferred<DetailAlbum> {
        return albumsRepository.getDetailAlbum(albumName, artistName)
    }

    fun getFavoritesAlbums() : Deferred<List<DetailAlbum>> {
        return albumsRepository.getFavoritesAlbum()
    }

    fun isFavoriteAlbum(artistName: String, albumName: String): Deferred<Boolean> {
        return albumsRepository.isFavoriteAlbum(artistName, albumName)
    }

    fun addAlbumToFavorites(album: DetailAlbum) {
        albumsRepository.addAlbumToFavorites(album)
    }

    fun removeAlbumFromFavorites(album: DetailAlbum) {
        albumsRepository.removeAlbumFromFavorites(album)
    }
}