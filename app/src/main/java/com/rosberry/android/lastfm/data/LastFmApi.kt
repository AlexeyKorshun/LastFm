/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.data

import com.rosberry.android.lastfm.entity.Album
import com.rosberry.android.lastfm.entity.Artist
import com.rosberry.android.lastfm.entity.DetailAlbum
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Alexei Korshun on 03/02/2019.
 */
interface LastFmApi {

    @GET("2.0/?method=artist.search&api_key=d5f514d16d94cf3435e4a1d9b2b1872d&format=json")
    fun searchArtist(@Query("artist") query: String): Deferred<List<Artist>>

    @GET("2.0/?method=artist.gettopalbums&api_key=d5f514d16d94cf3435e4a1d9b2b1872d&format=json")
    fun getTopAlbums(@Query("artist") query: String): Deferred<List<Album>>

    @GET("2.0/?method=album.getinfo&api_key=d5f514d16d94cf3435e4a1d9b2b1872d&format=json")
    fun getAlbumDetails(
            @Query("artist") artist: String,
            @Query("album") album: String
    ): Deferred<DetailAlbum>
}