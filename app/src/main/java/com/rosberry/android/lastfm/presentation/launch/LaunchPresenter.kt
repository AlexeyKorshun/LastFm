/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.launch

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.rosberry.android.lastfm.Screens
import com.rosberry.android.lastfm.base.router.AppRouter

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@InjectViewState
class LaunchPresenter constructor(
        private val router: AppRouter
) : MvpPresenter<MvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(Screens.MainScreen())
    }
}