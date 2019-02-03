/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.main.search

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.ui.AppFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.searchImageView.setOnClickListener { presenter.clickSearch(searchEditText.text.toString()) }
    }

    override fun showEmpty() {
    }

    override fun showEmptySearch() {
    }

    override fun showLoading() {
    }

    override fun showResult() {
    }

    override fun showError() {
    }
}