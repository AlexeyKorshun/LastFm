/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.data.search

import com.rosberry.android.lastfm.data.LastFmApi
import com.rosberry.android.lastfm.domain.search.SearchRepository
import com.rosberry.android.lastfm.entity.Artist
import kotlinx.coroutines.Deferred

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class SearchRepositoryImp(
        private val api: LastFmApi
) : SearchRepository {

    override suspend fun searchArtists(query: String): Deferred<List<Artist>> {
        return api.searchArtist(query)
    }
}