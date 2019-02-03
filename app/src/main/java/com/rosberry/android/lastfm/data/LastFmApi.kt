/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.data

import com.rosberry.android.lastfm.entity.Artist
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Alexei Korshun on 03/02/2019.
 */
interface LastFmApi {

    @GET("2.0/?method=artist.search&api_key=d5f514d16d94cf3435e4a1d9b2b1872d&format=json")
    fun searchArtist(@Query("artist") query: String): Deferred<List<Artist>>
}