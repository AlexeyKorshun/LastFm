/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.albums.top

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.recycler.ClickListener
import com.rosberry.android.lastfm.base.show
import com.rosberry.android.lastfm.base.ui.AppFragment
import com.rosberry.android.lastfm.di.PROP_ARTIST_NAME
import com.rosberry.android.lastfm.entity.Album
import com.rosberry.android.lastfm.entity.Artist
import com.rosberry.android.lastfm.presentation.albums.top.TopAlbumItem
import com.rosberry.android.lastfm.presentation.albums.top.TopAlbumsPresenter
import com.rosberry.android.lastfm.presentation.albums.top.TopAlbumsView
import kotlinx.android.synthetic.main.fragment_top_albums.*
import kotlinx.android.synthetic.main.fragment_top_albums.view.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.setProperty

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class TopAlbumsFragment : AppFragment(), TopAlbumsView, ClickListener<Album> {

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

    override val layoutRes: Int = R.layout.fragment_top_albums

    @InjectPresenter
    lateinit var presenter: TopAlbumsPresenter

    @ProvidePresenter
    fun providePresenter(): TopAlbumsPresenter {
        return getKoin().get()
    }

    private val adapter = TopAlbumsAdapter(emptyList(), this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        setProperty(PROP_ARTIST_NAME, arguments?.getString(BUNDLE_ARTIST_NAME) ?: "")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.albumsList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        view.albumsList.adapter = adapter
    }

    override fun showLoading() {
        progressBar.show(true)
        errorView.show(false)
        albumsList.show(false)
    }

    override fun showError(message: String) {
        errorView.text = message
        progressBar.show(false)
        errorView.show(true)
        albumsList.show(false)
    }

    override fun showResult(items: List<TopAlbumItem>) {
        adapter.update(items)
        progressBar.show(false)
        errorView.show(false)
        albumsList.show(true)
    }

    override fun click(item: Album) {
        presenter.click(item)
    }
}