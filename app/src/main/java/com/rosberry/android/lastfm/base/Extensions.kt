/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.base

import android.view.View

/**
 * @author Alexei Korshun on 03/02/2019.
 */
fun View.show(isShow: Boolean) {
    if (isShow) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}