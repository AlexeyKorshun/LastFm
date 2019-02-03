/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.top

import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.recycler.AbstractItem
import com.rosberry.android.lastfm.entity.Album

/**
 * @author Alexei Korshun on 03/02/2019.
 */
data class TopAlbumItem(
        val album: Album
) : AbstractItem(R.layout.item_album)