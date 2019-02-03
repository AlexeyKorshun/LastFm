/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.main.search

import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.recycler.AbstractItem
import com.rosberry.android.lastfm.entity.Artist

/**
 * @author Alexei Korshun on 03/02/2019.
 */
data class ArtistItem(
        val artist: Artist
): AbstractItem(R.layout.item_artist)