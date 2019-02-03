/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.main.search

import com.arellomobile.mvp.InjectViewState
import com.rosberry.android.lastfm.base.presentation.AppPresenter
import com.rosberry.android.lastfm.base.router.AppRouter

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@InjectViewState
class SearchPresenter(
        private val router: AppRouter
) : AppPresenter<SearchView>() {

    fun clickSearch(query: String) {

    }
}
