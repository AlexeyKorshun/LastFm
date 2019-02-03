/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.albums.top

import android.view.View
import com.rosberry.android.lastfm.base.recycler.AbstractViewHolder
import com.rosberry.android.lastfm.base.recycler.ClickListener
import com.rosberry.android.lastfm.entity.Album
import com.rosberry.android.lastfm.presentation.albums.top.TopAlbumItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album.view.*

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class TopAlbumViewHolder(
        view: View,
        private val clickListener: ClickListener<Album>
) : AbstractViewHolder<TopAlbumItem>(view) {

    override fun bind(item: TopAlbumItem) {
        super.bind(item)
        if (item.album.image.isNotBlank()) {
            Picasso.get()
                .load(item.album.image)
                .into(itemView.imageView)
        }
        itemView.nameView.text = item.album.name
    }
}