/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.entity

import java.io.Serializable

/**
 * @author Alexei Korshun on 03/02/2019.
 */
data class Artist(
        val id: String,
        val name: String,
        val image: String
) : Serializable