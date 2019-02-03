/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.albums.top

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.rosberry.android.lastfm.base.recycler.AbstractRecyclerAdapter
import com.rosberry.android.lastfm.base.recycler.AbstractViewHolder
import com.rosberry.android.lastfm.base.recycler.ClickListener
import com.rosberry.android.lastfm.base.recycler.DefaultDiffCallback
import com.rosberry.android.lastfm.entity.Album
import com.rosberry.android.lastfm.presentation.albums.top.TopAlbumItem

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class TopAlbumsAdapter(
        items: List<TopAlbumItem>,
        private val clickListener: ClickListener<Album>
) : AbstractRecyclerAdapter(items) {

    override fun createViewHolder(view: View, viewType: Int): AbstractViewHolder<*> {
        return TopAlbumViewHolder(view, clickListener)
    }

    fun update(newItems: List<TopAlbumItem>) {
        val diff = DiffUtil.calculateDiff(DefaultDiffCallback(items, newItems))
        items = newItems
        diff.dispatchUpdatesTo(this)
    }
}