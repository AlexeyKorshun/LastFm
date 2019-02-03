/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.main.search

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@StateStrategyType(SingleStateStrategy::class)
interface SearchView : MvpView {

    fun showEmpty()
    fun showEmptySearch()
    fun showLoading()
    fun showResult(items: List<ArtistItem>)
    fun showError()
}