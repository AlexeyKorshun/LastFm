/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import com.rosberry.android.lastfm.Screens
import com.rosberry.android.lastfm.base.presentation.AppPresenter
import com.rosberry.android.lastfm.base.router.AppRouter

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@InjectViewState
class MainPresenter(
        private val router: AppRouter
) : AppPresenter<MvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(Screens.FavoritesScreen())
    }

    fun clickSearch() {
        router.swapTo(Screens.SearchScreen())
    }

    fun clickFavorites() {
        router.swapTo(Screens.FavoritesScreen())
    }
}