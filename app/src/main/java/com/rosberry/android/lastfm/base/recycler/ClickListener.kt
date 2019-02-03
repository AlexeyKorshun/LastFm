/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.base.recycler

/**
 * @author Alexei Korshun on 03/02/2019.
 */
interface ClickListener<T> {

    fun click(item: T)
}