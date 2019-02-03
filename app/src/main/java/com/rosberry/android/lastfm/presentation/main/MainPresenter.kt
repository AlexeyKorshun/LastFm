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
import ru.terrakok.cicerone.Router

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@InjectViewState
class MainPresenter(
        private val router: Router
) : AppPresenter<MvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(Screens.FavoritesScreen())
    }

    fun clickSearch() {
        router.newRootScreen(Screens.SearchScreen())
    }

    fun clickFavorites() {
        router.newRootScreen(Screens.FavoritesScreen())
    }
}