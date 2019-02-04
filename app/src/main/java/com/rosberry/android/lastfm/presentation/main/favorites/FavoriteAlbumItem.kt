/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.main.favorites

import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.recycler.AbstractItem
import com.rosberry.android.lastfm.entity.DetailAlbum

/**
 * @author Alexei Korshun on 04/02/2019.
 */
data class FavoriteAlbumItem(
        val detailAlbum: DetailAlbum
) : AbstractItem(R.layout.item_favorite_album)