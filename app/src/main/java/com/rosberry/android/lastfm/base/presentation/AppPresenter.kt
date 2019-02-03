/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.base.presentation

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * @author Alexei Korshun on 03/02/2019.
 */
open class AppPresenter<V : MvpView> : MvpPresenter<V>() {

    protected val job = SupervisorJob()
    protected val uiScope = CoroutineScope(Dispatchers.Main + job)
    protected val bgScope = CoroutineScope(Dispatchers.IO + job)

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}