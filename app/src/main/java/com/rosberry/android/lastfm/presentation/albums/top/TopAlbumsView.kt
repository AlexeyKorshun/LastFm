/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.albums.top

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@StateStrategyType(SingleStateStrategy::class)
interface TopAlbumsView : MvpView {

    fun showLoading()
    fun showError(message: String)
    fun showResult(items: List<TopAlbumItem>)
}