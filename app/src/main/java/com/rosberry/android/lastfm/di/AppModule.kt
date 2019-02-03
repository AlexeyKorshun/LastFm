/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rosberry.android.lastfm.base.router.AppRouter
import com.rosberry.android.lastfm.data.LastFmApi
import com.rosberry.android.lastfm.data.search.LastFmConverterFactory
import com.rosberry.android.lastfm.data.search.SearchRepositoryImp
import com.rosberry.android.lastfm.domain.albums.AlbumsInteractor
import com.rosberry.android.lastfm.domain.search.SearchInteractor
import com.rosberry.android.lastfm.domain.search.SearchRepository
import com.rosberry.android.lastfm.presentation.albums.top.TopAlbumsPresenter
import com.rosberry.android.lastfm.presentation.launch.LaunchPresenter
import com.rosberry.android.lastfm.presentation.main.MainPresenter
import com.rosberry.android.lastfm.presentation.main.search.SearchPresenter
import org.koin.dsl.module.module
import retrofit2.Retrofit
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

/**
 * @author Alexei Korshun on 03/02/2019.
 */
const val APP_CICERONE = "app_cicerone"
const val MAIN_SCREEN_CICERONE = "bottom_navigation_cicerone"

const val PROP_ARTIST_NAME = "prop_artist_name"
const val PROP_ARTIST_ID = "prop_artist_id"

private const val BASE_URL = "http://ws.audioscrobbler.com"

val appModule = module {

    single(name = APP_CICERONE) { Cicerone.create(AppRouter()) }
    single(name = APP_CICERONE) { createRouter(get(name = APP_CICERONE)) }
    single(name = APP_CICERONE) { createNavigatorHolder(get(name = APP_CICERONE)) }

    single(name = MAIN_SCREEN_CICERONE) { Cicerone.create(AppRouter()) }
    single(name = MAIN_SCREEN_CICERONE) { createRouter(get(name = MAIN_SCREEN_CICERONE)) }
    single(name = MAIN_SCREEN_CICERONE) { createNavigatorHolder(get(name = MAIN_SCREEN_CICERONE)) }

    single { createRetrofit() }
    single { createApi(get()) }
    factory { SearchRepositoryImp(get()) } bind(SearchRepository::class)

    factory { SearchInteractor(get()) }
    factory { AlbumsInteractor() }

    factory { LaunchPresenter(get(name = APP_CICERONE)) }
    factory { MainPresenter(get(name = MAIN_SCREEN_CICERONE)) }
    factory { SearchPresenter(get(name = APP_CICERONE), get()) }
    factory { TopAlbumsPresenter(get(name = APP_CICERONE), get(), getProperty(PROP_ARTIST_NAME)) }
}

fun createRouter(cicerone: Cicerone<AppRouter>): AppRouter = cicerone.router
fun createNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder
fun createRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(LastFmConverterFactory())
    .build()

fun createApi(retrofit: Retrofit): LastFmApi = retrofit.create(LastFmApi::class.java)

val lastFmModules = listOf(appModule)