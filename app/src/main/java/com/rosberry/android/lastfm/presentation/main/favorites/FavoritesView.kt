/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.main.favorites

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Alexei Korshun on 04/02/2019.
 */
@StateStrategyType(SingleStateStrategy::class)
interface FavoritesView : MvpView {

    fun showFavorites(items: List<FavoriteAlbumItem>)
    fun showError(message: String)
}