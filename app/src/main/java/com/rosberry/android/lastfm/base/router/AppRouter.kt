/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.base.router

import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class AppRouter : Router() {

    fun swapTo(screen: SupportAppScreen) {
        executeCommands(SwapCommand(screen))
    }
}