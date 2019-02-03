/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.albums.top

import android.os.Bundle
import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.ui.AppFragment
import com.rosberry.android.lastfm.entity.Artist

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class TopAlbumsFragment : AppFragment() {

    companion object {
        private const val BUNDLE_ARTIST: String = "bundle_artist"

        fun newInstance(artist: Artist): TopAlbumsFragment {
            val fragment = TopAlbumsFragment()

            val bundle = Bundle().apply {
                this.putSerializable(BUNDLE_ARTIST, artist)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override val layoutRes: Int = R.layout.fragment_top_albums
}