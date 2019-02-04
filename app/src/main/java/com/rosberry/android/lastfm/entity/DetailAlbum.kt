/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.entity

/**
 * @author Alexei Korshun on 04/02/2019.
 */
data class DetailAlbum(
        val name: String,
        val artistName: String,
        val cover: String,
        val tracks: List<Track>
)