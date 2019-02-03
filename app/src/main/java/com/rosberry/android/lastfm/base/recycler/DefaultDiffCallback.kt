/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.base.recycler

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class DefaultDiffCallback(
        private val oldItems: List<AbstractItem>,
        private val newItems: List<AbstractItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val oldItem = oldItems[oldPosition]
        val newItem = newItems[newPosition]

        return oldItem == newItem
    }

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val oldItem = oldItems[oldPosition]
        val newItem = newItems[newPosition]

        return oldItem == newItem
    }

}