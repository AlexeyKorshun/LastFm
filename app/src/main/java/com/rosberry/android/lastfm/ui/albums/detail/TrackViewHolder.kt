/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.albums.detail

import android.view.View
import com.rosberry.android.lastfm.base.recycler.AbstractViewHolder
import com.rosberry.android.lastfm.presentation.albums.detail.TrackItem
import kotlinx.android.synthetic.main.item_track.view.*

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class TrackViewHolder(
        view: View
) : AbstractViewHolder<TrackItem>(view) {

    override fun bind(item: TrackItem) {
        itemView.trackName.text = item.track.name
    }
}