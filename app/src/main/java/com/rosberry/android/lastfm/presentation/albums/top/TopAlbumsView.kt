/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.top

/**
 * @author Alexei Korshun on 03/02/2019.
 */
interface TopAlbumsView {

    fun showLoading()
    fun showError(message: String)
    fun showResult(items: List<TopAlbumItem>)
}