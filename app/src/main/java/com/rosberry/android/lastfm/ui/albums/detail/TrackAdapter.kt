/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.albums.detail

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.rosberry.android.lastfm.base.recycler.AbstractRecyclerAdapter
import com.rosberry.android.lastfm.base.recycler.AbstractViewHolder
import com.rosberry.android.lastfm.base.recycler.DefaultDiffCallback
import com.rosberry.android.lastfm.presentation.albums.detail.TrackItem

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class TrackAdapter(
        items: List<TrackItem>
) : AbstractRecyclerAdapter(items) {

    override fun createViewHolder(view: View, viewType: Int): AbstractViewHolder<*> {
        return TrackViewHolder(view)
    }

    fun update(newItems: List<TrackItem>) {
        val diff = DiffUtil.calculateDiff(DefaultDiffCallback(items, newItems))
        items = newItems
        diff.dispatchUpdatesTo(this)
    }
}