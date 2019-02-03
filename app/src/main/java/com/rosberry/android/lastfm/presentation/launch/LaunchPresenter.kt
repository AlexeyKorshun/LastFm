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
import ru.terrakok.cicerone.Router

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@InjectViewState
class LaunchPresenter constructor(
        private val router: Router
) : MvpPresenter<MvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(Screens.MainScreen())
    }
}