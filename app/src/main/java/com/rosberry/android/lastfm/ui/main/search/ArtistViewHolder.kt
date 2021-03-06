/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.main.search

import android.view.View
import com.rosberry.android.lastfm.base.recycler.AbstractViewHolder
import com.rosberry.android.lastfm.base.recycler.ClickListener
import com.rosberry.android.lastfm.entity.Artist
import com.rosberry.android.lastfm.presentation.main.search.ArtistItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_artist.view.*

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class ArtistViewHolder(
        view: View,
        private val clickListener: ClickListener<Artist>
) : AbstractViewHolder<ArtistItem>(view) {

    override fun bind(item: ArtistItem) {
        super.bind(item)
        if (item.artist.image.isNotBlank()) {
            Picasso.get()
                .load(item.artist.image)
                .into(itemView.imageView)
        }
        itemView.nameView.text = item.artist.name
        itemView.setOnClickListener { clickListener.click(item.artist) }
    }
}