/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.base.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Alexei Korshun on 03/02/2019.
 */
abstract class AbstractViewHolder<in ITEM : AbstractItem>(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: ITEM) {}
}