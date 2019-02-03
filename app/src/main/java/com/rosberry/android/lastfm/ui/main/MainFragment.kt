/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rosberry.android.lastfm.R
import com.rosberry.android.lastfm.base.ui.AppFragment
import com.rosberry.android.lastfm.di.MAIN_SCREEN_CICERONE
import com.rosberry.android.lastfm.presentation.main.MainPresenter
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class MainFragment : AppFragment(), MvpView, BottomNavigationView.OnNavigationItemSelectedListener {

    override val layoutRes: Int = R.layout.fragment_main

    private val navigatorHolder: NavigatorHolder by inject(name = MAIN_SCREEN_CICERONE)
    private val navigator: Navigator by lazy {
        SupportAppNavigator(this.activity, childFragmentManager, R.id.mainContainer)
    }

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return getKoin().get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorites -> {
                presenter.clickFavorites()
                true
            }
            R.id.action_search -> {
                presenter.clickSearch()
                true
            }
            else -> false
        }
    }

}