/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.albums.top

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.loader.app.LoaderManager
import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.recycler.ClickListener
import com.rosberry.android.lastfm.databinding.FragmentTopAlbumsBinding
import com.rosberry.android.lastfm.di.PROP_ARTIST_NAME
import com.rosberry.android.lastfm.entity.Album
import com.rosberry.android.lastfm.entity.Artist
import com.rosberry.android.lastfm.presentation.albums.top.TopAlbumsPresenter
import de.appsfactory.mvplib.view.MVPFragment
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.setProperty

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class TopAlbumsFragment : MVPFragment<TopAlbumsPresenter>(), ClickListener<Album> {

    companion object {
        private const val BUNDLE_ARTIST_NAME: String = "bundle_artist_name"
        private const val BUNDLE_ARTIST_ID: String = "bundle_artist_id"

        fun newInstance(artist: Artist): TopAlbumsFragment {
            val fragment = TopAlbumsFragment()

            val bundle = Bundle().apply {
                this.putString(BUNDLE_ARTIST_NAME, artist.name)
                this.putString(BUNDLE_ARTIST_ID, artist.id)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun createPresenter(): TopAlbumsPresenter = getKoin().get()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        setProperty(PROP_ARTIST_NAME, arguments?.getString(BUNDLE_ARTIST_NAME) ?: "")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.init(requireContext(), LoaderManager.getInstance(this))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentTopAlbumsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_albums, container, false)
        binding.presenter = mPresenter
        return binding.root
    }

    override fun click(item: Album) {
        mPresenter.click(item)
    }
}