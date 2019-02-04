/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.main.favorites

import android.view.View
import com.rosberry.android.lastfm.base.recycler.AbstractViewHolder
import com.rosberry.android.lastfm.base.recycler.ClickListener
import com.rosberry.android.lastfm.entity.DetailAlbum
import com.rosberry.android.lastfm.presentation.main.favorites.FavoriteAlbumItem
import kotlinx.android.synthetic.main.item_favorite_album.view.*

/**
 * @author Alexei Korshun on 04/02/2019.
 */
class FavoriteAlbumViewHolder(
        view: View,
        private val clickListener: ClickListener<DetailAlbum>
) : AbstractViewHolder<FavoriteAlbumItem>(view) {

    override fun bind(item: FavoriteAlbumItem) {
        super.bind(item)
        itemView.albumName.text = item.detailAlbum.name
        itemView.artistName.text = item.detailAlbum.artistName
        itemView.setOnClickListener { clickListener.click(item.detailAlbum) }
    }
}