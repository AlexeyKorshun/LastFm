/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.di

import com.rosberry.android.lastfm.presentation.launch.LaunchPresenter
import org.koin.dsl.module.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

/**
 * @author Alexei Korshun on 03/02/2019.
 */
val appModule = module {

    single { Cicerone.create() }
    single { createRouter(get()) }
    single { createNavigatorHolder(get()) }
    single { LaunchPresenter(get()) }
}

fun createRouter(cicerone: Cicerone<Router>): Router = cicerone.router
fun createNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder