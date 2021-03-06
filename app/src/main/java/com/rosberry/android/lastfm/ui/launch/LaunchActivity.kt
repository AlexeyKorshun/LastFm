/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.launch

import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.router.AppNavigator
import com.rosberry.android.lastfm.base.ui.AppActivity
import com.rosberry.android.lastfm.di.APP_CICERONE
import com.rosberry.android.lastfm.di.lastFmModules
import com.rosberry.android.lastfm.presentation.launch.LaunchPresenter
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder

class LaunchActivity : AppActivity(), MvpView {

    override val layoutRes: Int = R.layout.activity_launch

    override val navigatorHolder: NavigatorHolder by inject(name = APP_CICERONE)
    override val navigator: Navigator = AppNavigator(this, supportFragmentManager, R.id.content)

    @InjectPresenter
    lateinit var presenter: LaunchPresenter

    @ProvidePresenter
    fun providePresenter(): LaunchPresenter {
        return getKoin().get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) startKoin(this.applicationContext, lastFmModules)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) stopKoin()
    }

}
