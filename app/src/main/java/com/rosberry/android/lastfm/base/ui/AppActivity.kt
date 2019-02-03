/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.base.ui

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder

/**
 * @author Alexei Korshun on 03/02/2019.
 */
abstract class AppActivity : MvpAppCompatActivity() {

    protected abstract val layoutRes: Int

    protected abstract val navigatorHolder: NavigatorHolder
    protected abstract val navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {

        val fragmentList = supportFragmentManager.fragments

        var handled = false
        for (fragment in fragmentList) {
            if (fragment is AppFragment) {
                handled = fragment.onBackPressed()
                if (handled) break
            }
        }

        if (!handled) {
            super.onBackPressed()
        }
    }
}