/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.presentation.main.search

import com.arellomobile.mvp.InjectViewState
import com.rosberry.android.lastfm.Screens
import com.rosberry.android.lastfm.base.presentation.AppPresenter
import com.rosberry.android.lastfm.base.router.AppRouter
import com.rosberry.android.lastfm.domain.search.SearchInteractor
import com.rosberry.android.lastfm.entity.Artist
import kotlinx.coroutines.launch

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
                val artists = searchInteractor.searchArtists(query)
                    .await()
                viewState.showResult(artists.converToItems())
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showError(e.localizedMessage)
            }
        }
    }

    fun queryChanged(query: String) {
        viewState.enableSearch(searchInteractor.isValidQuery(query))
    }

    fun clickItem(item: Artist) {
        router.navigateTo(Screens.TopAlbumsScreen(item))
    }
}

private fun List<Artist>.converToItems(): List<ArtistItem> {
    return this.asSequence()
        .map { ArtistItem(it) }
        .toList()
}
