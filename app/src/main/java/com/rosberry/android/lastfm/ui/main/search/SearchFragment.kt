/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.main.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.show
import com.rosberry.android.lastfm.base.ui.AppFragment
import com.rosberry.android.lastfm.presentation.main.search.ArtistItem
import com.rosberry.android.lastfm.presentation.main.search.SearchPresenter
import com.rosberry.android.lastfm.presentation.main.search.SearchView
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import org.koin.android.ext.android.getKoin

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class SearchFragment : AppFragment(), SearchView {

    override val layoutRes: Int = R.layout.fragment_search

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter(): SearchPresenter {
        return getKoin().get()
    }

    private val adapter = ArtistAdapter(emptyList())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.searchImageView.setOnClickListener { presenter.clickSearch(searchEditText.text.toString()) }
        view.artistsList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        view.artistsList.adapter = adapter
    }

    override fun showEmpty() {
        emptySearchText.show(false)
        progressBar.show(false)
        errorView.show(false)
        artistsList.show(false)
    }

    override fun showEmptySearch() {
        emptySearchText.show(true)
        progressBar.show(false)
        errorView.show(false)
        artistsList.show(false)
    }

    override fun showLoading() {
        emptySearchText.show(false)
        progressBar.show(true)
        errorView.show(false)
        artistsList.show(false)
    }

    override fun showResult(items: List<ArtistItem>) {
        emptySearchText.show(false)
        progressBar.show(false)
        errorView.show(false)
        artistsList.show(true)
        adapter.update(items)
    }

    override fun showError() {
        emptySearchText.show(false)
        progressBar.show(false)
        errorView.show(true)
        artistsList.show(false)
    }
}