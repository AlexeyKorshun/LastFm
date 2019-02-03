/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.main.search

import com.arellomobile.mvp.InjectViewState
import com.rosberry.android.lastfm.base.presentation.AppPresenter
import com.rosberry.android.lastfm.base.router.AppRouter
import com.rosberry.android.lastfm.domain.search.SearchInteractor
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Alexei Korshun on 03/02/2019.
 */
@InjectViewState
class SearchPresenter(
        private val router: AppRouter,
        private val searchInteractor: SearchInteractor
) : AppPresenter<SearchView>() {

    fun clickSearch(query: String) {
        uiScope.launch {
            viewState.showLoading()
            try {
                val artists = withContext(bgScope.coroutineContext) {
                    searchInteractor.search(query)
                        .await()
                }
                val itemsList = artists.asSequence()
                    .map { ArtistItem(it) }
                    .toList()
                viewState.showResult(itemsList)
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showError(e.localizedMessage)
            }
        }
    }

    fun queryChanged(query: String) {
        viewState.enableSearch(searchInteractor.isValidQuery(query))
    }
}
