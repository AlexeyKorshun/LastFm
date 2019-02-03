/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.di

import com.rosberry.android.lastfm.base.router.AppRouter
import com.rosberry.android.lastfm.presentation.launch.LaunchPresenter
import com.rosberry.android.lastfm.presentation.main.MainPresenter
import org.koin.dsl.module.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

const val APP_CICERONE = "app_cicerone"
const val MAIN_SCREEN_CICERONE = "bottom_navigation_cicerone"
/**
 * @author Alexei Korshun on 03/02/2019.
 */
val appModule = module {

    single(name = APP_CICERONE) { Cicerone.create(AppRouter()) }
    single(name = APP_CICERONE) { createRouter(get(name = APP_CICERONE)) }
    single(name = APP_CICERONE) { createNavigatorHolder(get(name = APP_CICERONE)) }

    single(name = MAIN_SCREEN_CICERONE) { Cicerone.create(AppRouter()) }
    single(name = MAIN_SCREEN_CICERONE) { createRouter(get(name = MAIN_SCREEN_CICERONE)) }
    single(name = MAIN_SCREEN_CICERONE) { createNavigatorHolder(get(name = MAIN_SCREEN_CICERONE)) }

    factory { LaunchPresenter(get(name = APP_CICERONE)) }
    factory { MainPresenter(get(name = MAIN_SCREEN_CICERONE)) }
}

fun createRouter(cicerone: Cicerone<AppRouter>): AppRouter = cicerone.router
fun createNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder

val lastFmModules = listOf(appModule)