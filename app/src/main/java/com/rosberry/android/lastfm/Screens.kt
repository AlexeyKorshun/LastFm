/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm

import androidx.fragment.app.Fragment
import com.rosberry.android.lastfm.ui.main.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * @author Alexei Korshun on 03/02/2019.
 */
object Screens {

    class MainScreen : SupportAppScreen() {

        override fun getFragment(): Fragment = MainFragment()
    }
}