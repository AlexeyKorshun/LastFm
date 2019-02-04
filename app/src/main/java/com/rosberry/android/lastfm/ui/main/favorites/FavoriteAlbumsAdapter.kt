/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.main.favorites

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.rosberry.android.lastfm.base.recycler.AbstractRecyclerAdapter
import com.rosberry.android.lastfm.base.recycler.AbstractViewHolder
import com.rosberry.android.lastfm.base.recycler.ClickListener
import com.rosberry.android.lastfm.base.recycler.DefaultDiffCallback
import com.rosberry.android.lastfm.entity.DetailAlbum
import com.rosberry.android.lastfm.presentation.main.favorites.FavoriteAlbumItem

/**
 * @author Alexei Korshun on 04/02/2019.
 */
class FavoriteAlbumsAdapter(
        items: List<FavoriteAlbumItem>,
        private val clickListener: ClickListener<DetailAlbum>
) : AbstractRecyclerAdapter(items) {

    override fun createViewHolder(view: View, viewType: Int): AbstractViewHolder<*> {
        return FavoriteAlbumViewHolder(view, clickListener)
    }

    fun update(newItems: List<FavoriteAlbumItem>) {
        val diff = DiffUtil.calculateDiff(DefaultDiffCallback(items, newItems))
        items = newItems
        diff.dispatchUpdatesTo(this)
    }
}