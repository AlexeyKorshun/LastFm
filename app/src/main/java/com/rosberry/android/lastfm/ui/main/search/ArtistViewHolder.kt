/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.main.search

import android.view.View
import com.rosberry.android.lastfm.base.recycler.AbstractViewHolder
import com.rosberry.android.lastfm.presentation.main.search.ArtistItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_artist.view.*

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class ArtistViewHolder(
        view: View
) : AbstractViewHolder<ArtistItem>(view) {

    override fun bind(item: ArtistItem) {
        super.bind(item)
        Picasso.get()
            .load(item.artist.image)
            .into(itemView.imageView)

        itemView.nameView.text = item.artist.name
    }
}