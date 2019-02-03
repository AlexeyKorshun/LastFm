/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.base.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Alexei Korshun on 03/02/2019.
 */
abstract class AbstractRecyclerAdapter(
        protected var items: List<AbstractItem>
) : RecyclerView.Adapter<AbstractViewHolder<AbstractItem>>() {

    abstract fun createViewHolder(view: View, viewType: Int): AbstractViewHolder<*>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<AbstractItem> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return createViewHolder(view, viewType) as AbstractViewHolder<AbstractItem>
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<AbstractItem>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].type
}