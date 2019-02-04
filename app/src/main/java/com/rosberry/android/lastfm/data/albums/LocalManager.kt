/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.data.albums

import android.content.Context
import com.rosberry.android.lastfm.entity.DetailAlbum
import com.rosberry.android.lastfm.entity.Track
import org.json.JSONArray
import org.json.JSONObject

/**
 * @author Alexei Korshun on 04/02/2019.
 */
class LocalManager(
        private val context: Context
) {

    fun store(albums: Collection<DetailAlbum>) {
        val jsonArray = JSONArray()

        albums.asSequence()
            .forEach {
                val jsonObject = JSONObject()
                jsonObject.put("name", it.name)
                jsonObject.put("artistName", it.artistName)
                jsonObject.put("cover", it.cover)
                jsonObject.put("tracks", it.tracks.createJsonTracks())

                jsonArray.put(jsonObject)
            }

        context.getSharedPreferences("lastfm", Context.MODE_PRIVATE)
            .edit()
            .putString("json", jsonArray.toString())
            .apply()
    }

    fun restore(): Collection<DetailAlbum> {
        val string: String = context.getSharedPreferences("lastfm", Context.MODE_PRIVATE)
            .getString("json", "") ?: ""

        val result = mutableListOf<DetailAlbum>()

        if (string.isNotBlank()) {
            val jsonArray = JSONArray(string)
            for (index in 0 until jsonArray.length()) {
                val jsonAlbum = jsonArray.getJSONObject(index)
                val detailAlbum = DetailAlbum(
                        jsonAlbum.optString("name"),
                        jsonAlbum.optString("artistName"),
                        jsonAlbum.optString("cover"),
                        jsonAlbum.getJSONArray("tracks").convertToTracks()
                )

                result.add(detailAlbum)
            }
        }

        return result
    }
}

private fun List<Track>.createJsonTracks(): JSONArray {
    val jsonArray = JSONArray()
    this.asSequence()
        .forEach { jsonArray.put(it.name) }
    return jsonArray
}

private fun JSONArray.convertToTracks(): List<Track> {
    val result = mutableListOf<Track>()
    for (index in 0 until this.length()) {
        result.add(Track(this.getString(index)))
    }

    return result
}