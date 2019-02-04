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
import com.rosberry.android.lastfm.entity.Track
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class AlbumsRepositoryImpl(
        private val api: LastFmApi
) : AlbumsRepository {

    override fun getTopAlbums(artistName: String): Deferred<List<Album>> {
        return api.getTopAlbums(artistName)
    }

    override suspend fun getLocalAlbums(): List<Album> {
        return emptyList()
    }

    override fun getDetailAlbum(albumName: String, artistName: String): Deferred<DetailAlbum> {
        val deff = CompletableDeferred<DetailAlbum>()
        deff.complete(
                DetailAlbum(
                        "Album",
                        "artist",
                        "",
                        listOf(
                                Track("first"),
                                Track("first"),
                                Track("first"),
                                Track("first"),
                                Track("first"),
                                Track("first")
                        )
                )
        )
        return deff
    }
}