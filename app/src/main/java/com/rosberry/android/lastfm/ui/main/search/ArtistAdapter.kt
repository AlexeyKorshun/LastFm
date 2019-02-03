/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.main.search

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.rosberry.android.lastfm.base.recycler.AbstractRecyclerAdapter
import com.rosberry.android.lastfm.base.recycler.AbstractViewHolder
import com.rosberry.android.lastfm.base.recycler.ClickListener
import com.rosberry.android.lastfm.base.recycler.DefaultDiffCallback
import com.rosberry.android.lastfm.entity.Artist
import com.rosberry.android.lastfm.presentation.main.search.ArtistItem

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class ArtistAdapter(
        items: List<ArtistItem>,
        private val itemsClickListener: ClickListener<Artist>
) : AbstractRecyclerAdapter(items) {

    override fun createViewHolder(view: View, viewType: Int): AbstractViewHolder<*> {
        return ArtistViewHolder(view, itemsClickListener)
    }

    fun update(newItems: List<ArtistItem>) {
        val diff = DiffUtil.calculateDiff(DefaultDiffCallback(items, newItems))
        items = newItems
        diff.dispatchUpdatesTo(this)
    }
}