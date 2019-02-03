/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.domain.albums

import com.rosberry.android.lastfm.entity.Album
import kotlinx.coroutines.Deferred

/**
 * @author Alexei Korshun on 03/02/2019.
 */
interface AlbumsRepository {

    fun getTopAlbums(artistName: String) : Deferred<List<Album>>

    fun getLocalAlbums() : Deferred<List<Album>>
}