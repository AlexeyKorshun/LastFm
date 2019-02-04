/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.data.albums

import com.rosberry.android.lastfm.data.LastFmApi
import com.rosberry.android.lastfm.domain.albums.AlbumsRepository
import com.rosberry.android.lastfm.entity.Album
import com.rosberry.android.lastfm.entity.DetailAlbum
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class AlbumsRepositoryImpl(
        private val api: LastFmApi
) : AlbumsRepository {

    private val favoritesAlbums: MutableMap<String, DetailAlbum> = mutableMapOf()

    override fun getTopAlbums(artistName: String): Deferred<List<Album>> {
        return api.getTopAlbums(artistName)
    }

    override fun getDetailAlbum(albumName: String, artistName: String): Deferred<DetailAlbum> {
        return api.getAlbumDetails(artistName, albumName)
    }

    override fun getFavoritesAlbum(): Deferred<List<DetailAlbum>> {
        val deferred = CompletableDeferred<List<DetailAlbum>>()
        deferred.complete(favoritesAlbums.values.toList())
        return deferred
    }

    override fun isFavoriteAlbum(artistName: String, albumName: String): Deferred<Boolean> {
        val deferred = CompletableDeferred<Boolean>()
        deferred.complete(favoritesAlbums.containsKey("$artistName-$albumName"))
        return deferred
    }

    override fun addAlbumToFavorites(album: DetailAlbum) {
        favoritesAlbums["${album.artistName}-${album.name}"] = album
    }

    override fun removeAlbumFromFavorites(album: DetailAlbum) {
        favoritesAlbums.remove("${album.artistName}-${album.name}")
    }
}