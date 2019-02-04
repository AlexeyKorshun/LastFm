/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.albums.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.show
import com.rosberry.android.lastfm.base.ui.AppFragment
import com.rosberry.android.lastfm.di.PROP_ALBUM_NAME
import com.rosberry.android.lastfm.di.PROP_ARTIST_NAME
import com.rosberry.android.lastfm.presentation.albums.detail.AlbumDetailPresenter
import com.rosberry.android.lastfm.presentation.albums.detail.AlbumDetailView
import com.rosberry.android.lastfm.presentation.albums.detail.TrackItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_detail.*
import kotlinx.android.synthetic.main.fragment_album_detail.view.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.setProperty

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class AlbumDetailFragment : AppFragment(), AlbumDetailView {

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

    @InjectPresenter
    lateinit var presenter: AlbumDetailPresenter

    @ProvidePresenter
    fun providePresenter(): AlbumDetailPresenter {
        return getKoin().get()
    }

    private val adapter = TrackAdapter(emptyList())

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        setProperty(PROP_ARTIST_NAME, arguments?.getString(BUNDLE_ARTIST_NAME) ?: "")
        setProperty(PROP_ALBUM_NAME, arguments?.getString(BUNDLE_ALBUM_NAME) ?: "")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.trackList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        view.trackList.adapter = adapter
    }

    override fun showLoading() {
        imageView.show(false)
        albumName.show(false)
        artistName.show(false)
        trackList.show(false)
        progressBar.show(true)
        errorView.show(false)
        tracksTitle.show(false)
    }

    override fun showError(message: String) {
        errorView.text = message
        imageView.show(false)
        albumName.show(false)
        artistName.show(false)
        trackList.show(false)
        progressBar.show(false)
        errorView.show(true)
        tracksTitle.show(false)
    }

    override fun showTracks(tracks: List<TrackItem>) {
        adapter.update(tracks)
        trackList.show(true)
        progressBar.show(false)
        errorView.show(false)
        tracksTitle.show(true)
    }

    override fun showAlbumName(name: String) {
        albumName.text = name
        albumName.show(true)
        progressBar.show(false)
        errorView.show(false)
    }

    override fun showArtistName(name: String) {
        artistName.text = name
        artistName.show(true)
        progressBar.show(false)
        errorView.show(false)
    }

    override fun showCover(url: String) {
        if (url.isNotBlank()) {
            Picasso.get()
                .load(url)
                .into(imageView)
        }
        imageView.show(true)
        progressBar.show(false)
        errorView.show(false)
    }

}