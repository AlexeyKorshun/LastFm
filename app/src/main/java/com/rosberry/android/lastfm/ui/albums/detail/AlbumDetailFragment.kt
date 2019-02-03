/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.albums.detail

import android.os.Bundle
import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.ui.AppFragment

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class AlbumDetailFragment : AppFragment() {

    companion object {
        private const val BUNDLE_ARTIST_NAME = "bundle_artist_name"
        private const val BUNDLE_ALBUM_NAME = "bundle_album_name"

        fun newInstance(artistName: String, albumName: String): AlbumDetailFragment {
            val fragment = AlbumDetailFragment()
            val args = Bundle().apply {
                putString(BUNDLE_ARTIST_NAME, artistName)
                putString(BUNDLE_ALBUM_NAME, albumName)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override val layoutRes: Int = R.layout.fragment_album_detail

}