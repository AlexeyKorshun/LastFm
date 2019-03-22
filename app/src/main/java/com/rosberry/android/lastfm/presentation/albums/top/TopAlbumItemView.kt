/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.top

import com.rosberry.android.lastfm.entity.Album
import de.appsfactory.mvplib.presenter.MVPEvents

/**
 * @author Alexei Korshun on 22/03/2019.
 */
interface TopAlbumItemView : MVPEvents {

    fun click(album: Album)
}