/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.main.favorites

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
import com.rosberry.android.lastfm.entity.DetailAlbum
import com.rosberry.android.lastfm.presentation.main.favorites.FavoriteAlbumItem
import com.rosberry.android.lastfm.presentation.main.favorites.FavoritesPresenter
import com.rosberry.android.lastfm.presentation.main.favorites.FavoritesView
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_favorites.view.*
import org.koin.android.ext.android.getKoin

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class FavoritesFragment : AppFragment(), FavoritesView, ClickListener<DetailAlbum> {

    override val layoutRes: Int = R.layout.fragment_favorites

    @InjectPresenter
    lateinit var presenter: FavoritesPresenter

    @ProvidePresenter
    fun providePresenter(): FavoritesPresenter {
        return getKoin().get()
    }

    private val adapter = FavoriteAlbumsAdapter(emptyList(), this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.favoritesAlbumsList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        view.favoritesAlbumsList.adapter = adapter
    }

    override fun showFavorites(items: List<FavoriteAlbumItem>) {
        favoritesAlbumsList.show(true)
        errorView.show(false)
        adapter.update(items)
    }

    override fun showError(message: String) {
        errorView.text = message
        favoritesAlbumsList.show(false)
        errorView.show(true)
    }

    override fun click(item: DetailAlbum) {
        presenter.click(item)
    }
}