/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.domain.search

import com.rosberry.android.lastfm.entity.Artist
import kotlinx.coroutines.Deferred

/**
 * @author Alexei Korshun on 03/02/2019.
 */
interface SearchRepository {

    suspend fun searchArtists(query: String): Deferred<List<Artist>>
}