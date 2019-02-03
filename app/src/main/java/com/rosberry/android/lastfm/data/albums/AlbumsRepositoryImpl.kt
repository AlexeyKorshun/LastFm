/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.data.albums

import com.rosberry.android.lastfm.data.LastFmApi
import com.rosberry.android.lastfm.domain.albums.AlbumsRepository
import com.rosberry.android.lastfm.entity.Album
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
}