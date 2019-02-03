/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.main.search

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchView : MvpView {

    fun showEmpty()
    fun showEmptySearch()
    fun showLoading()
    fun showResult()
    fun showError()
}