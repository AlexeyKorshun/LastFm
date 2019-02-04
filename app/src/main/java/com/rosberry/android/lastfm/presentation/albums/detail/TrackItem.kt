/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.detail

import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.recycler.AbstractItem
import com.rosberry.android.lastfm.entity.Track

/**
 * @author Alexei Korshun on 03/02/2019.
 */
data class TrackItem(
        val track: Track
) : AbstractItem(R.layout.item_track)