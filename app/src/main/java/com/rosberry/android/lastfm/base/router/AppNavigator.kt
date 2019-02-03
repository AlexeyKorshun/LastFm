/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.base.router

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.rosberry.android.lastfm.base.ui.AppFragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class AppNavigator(
        activity: FragmentActivity?,
        private val fragmentManager: FragmentManager,
        private val containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun applyCommand(command: Command?) {
        if (command is SwapCommand) {
            swapFragment(command)
        } else {
            super.applyCommand(command)
        }
    }

    protected fun swapFragment(command: SwapCommand) {
        val currentFragment = fragmentManager.fragments.firstOrNull { !it.isHidden } as? AppFragment
        val newFragment = fragmentManager.findFragmentByTag(command.screen.screenKey)

        if (currentFragment != null && newFragment != null && currentFragment == newFragment) return

        fragmentManager.beginTransaction()
            .apply {
                if (newFragment == null) add(containerId, createFragment(command.screen), command.screen.screenKey)

                currentFragment?.let {
                    hide(it)
                    it.userVisibleHint = false
                }
                newFragment?.let {
                    show(it)
                    it.userVisibleHint = true
                }
            }
            .commitNow()

    }
}