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
        private val api: LastFmApi,
        private val localManager: LocalManager
) : AlbumsRepository {

    private val favoritesAlbums: MutableMap<String, DetailAlbum> = localManager.restore()
        .asSequence()
        .map { createAlbumKey(it.artistName, it.name) to it }
        .toMutableMap()

    override fun getTopAlbums(artistName: String): Deferred<List<Album>> {
        return api.getTopAlbums(artistName)
    }

    override fun getDetailAlbum(albumName: String, artistName: String): Deferred<DetailAlbum> {
        return if (favoritesAlbums.containsKey(createAlbumKey(artistName, albumName))) {
            val deferred = CompletableDeferred<DetailAlbum>()
            deferred.complete(favoritesAlbums[createAlbumKey(artistName, albumName)]!!)
            deferred
        } else api.getAlbumDetails(artistName, albumName)
    }

    override fun getFavoritesAlbum(): Deferred<List<DetailAlbum>> {
        val deferred = CompletableDeferred<List<DetailAlbum>>()
        deferred.complete(favoritesAlbums.values.toList())
        return deferred
    }

    override fun isFavoriteAlbum(artistName: String, albumName: String): Deferred<Boolean> {
        val deferred = CompletableDeferred<Boolean>()
        deferred.complete(favoritesAlbums.containsKey(createAlbumKey(artistName, albumName)))
        return deferred
    }

    override fun addAlbumToFavorites(album: DetailAlbum) {
        favoritesAlbums[createAlbumKey(album.artistName, album.name)] = album
        localManager.store(favoritesAlbums.values)
    }

    override fun removeAlbumFromFavorites(album: DetailAlbum) {
        favoritesAlbums.remove(createAlbumKey(album.artistName, album.name))
        localManager.store(favoritesAlbums.values)
    }

    private fun createAlbumKey(artistName: String, albumName: String): String {
        return "$artistName-$albumName"
    }

    private fun <K, V> Sequence<Pair<K, V>>.toMutableMap(): MutableMap<K, V> = toMap(LinkedHashMap())
}