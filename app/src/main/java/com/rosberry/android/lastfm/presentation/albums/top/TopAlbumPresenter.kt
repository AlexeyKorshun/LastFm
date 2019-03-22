/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.top

import com.rosberry.android.lastfm.BR
import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.entity.Album
import de.appsfactory.mvplib.annotations.MVPIncludeToState
import de.appsfactory.mvplib.presenter.MVPEventRecyclerItem
import de.appsfactory.mvplib.util.ObservableString

/**
 * @author Alexei Korshun on 22/03/2019.
 */
class TopAlbumPresenter(
        private val album: Album
) : MVPEventRecyclerItem<TopAlbumItemView>() {

    @MVPIncludeToState
    val name = ObservableString()

    @MVPIncludeToState
    val image = ObservableString()

    init {
        this.name.set(album.name)
        this.image.set(album.image)
    }

    override fun getLayoutId(): Int = R.layout.item_album

    override fun getItemId(): Int = BR.item

    fun onClick() {
        events.click(album)
    }
}