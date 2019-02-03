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
class SearchInteractor(
        private val searchRepository: SearchRepository
) {

    fun isValidQuery(query: String): Boolean = query.isNotBlank()

    suspend fun search(query: String): Deferred<List<Artist>> = searchRepository.searchArtists(query)
}