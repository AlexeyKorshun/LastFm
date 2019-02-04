/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.data

import com.rosberry.android.lastfm.entity.Album
import com.rosberry.android.lastfm.entity.Artist
import com.rosberry.android.lastfm.entity.DetailAlbum
import com.rosberry.android.lastfm.entity.Track
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @author Alexei Korshun on 03/02/2019.
 */
class LastFmConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>,
                                       retrofit: Retrofit): Converter<ResponseBody, *>? {
        val typeString = type.toString()
        return when (typeString) {
            "java.util.List<com.rosberry.android.lastfm.entity.Artist>" -> ArtistsConverter()
            "java.util.List<com.rosberry.android.lastfm.entity.Album>" -> AlbumConverter()
            "class com.rosberry.android.lastfm.entity.DetailAlbum" -> AlbumDetailConverter()
            else -> null
        }
    }

    class ArtistsConverter : Converter<ResponseBody, List<Artist>> {

        override fun convert(value: ResponseBody): List<Artist>? {
            val result = mutableListOf<Artist>()
            val rawResponse = value.string()
            val jsonResponse = JSONObject(rawResponse)

            if (jsonResponse.has("results")) {
                val jsonResults = jsonResponse.getJSONObject("results")
                if (jsonResults.has("artistmatches")) {
                    val jsonArtistsObject = jsonResults.getJSONObject("artistmatches")
                    val jsonArtistsList = jsonArtistsObject.getJSONArray("artist")
                    for (index in 0 until jsonArtistsList.length()) {
                        val jsonArtist = jsonArtistsList.getJSONObject(index)

                        val jsonImages = jsonArtist.getJSONArray("image")

                        result.add(Artist(
                                jsonArtist.optString("mbid"),
                                jsonArtist.optString("name"),
                                jsonImages.getImageFromArray()
                        ))
                    }
                }
            }
            return result
        }
    }

    class AlbumConverter : Converter<ResponseBody, List<Album>> {

        override fun convert(value: ResponseBody): List<Album>? {
            val jsonResponse = JSONObject(value.string())
            val result = mutableListOf<Album>()
            if (jsonResponse.has("topalbums")) {
                val jsonTopAlbums = jsonResponse.getJSONObject("topalbums")
                if (jsonTopAlbums.has("album")) {
                    val jsonAlbums = jsonTopAlbums.getJSONArray("album")

                    for (index in 0 until jsonAlbums.length()) {
                        val jsonAlbum = jsonAlbums.getJSONObject(index)
                        val jsonImages = jsonAlbum.getJSONArray("image")

                        result.add(Album(
                                jsonAlbum.optString("mbid"),
                                jsonAlbum.optString("name"),
                                jsonImages.getImageFromArray()
                        ))
                    }
                }
            }
            return result
        }
    }

    class AlbumDetailConverter : Converter<ResponseBody, DetailAlbum> {

        override fun convert(value: ResponseBody): DetailAlbum? {
            val jsonResponse = JSONObject(value.string())
            var result: DetailAlbum? = null
            if (jsonResponse.has("album")) {
                val jsonAlbum = jsonResponse.getJSONObject("album")
                val jsonImages = jsonAlbum.getJSONArray("image")
                val jsonTracks = jsonAlbum.getJSONObject("tracks")
                    .getJSONArray("track")
                result = DetailAlbum(
                        jsonAlbum.optString("name"),
                        jsonAlbum.optString("artist"),
                        jsonImages.getImageFromArray(),
                        jsonTracks.getTracksFromArray()
                )

            }
            return result
        }

    }
}

private fun JSONArray.getImageFromArray(): String {
    var image = ""
    for (imageIndex in 0 until this.length()) {
        val jsonImage = this.getJSONObject(imageIndex)
        if (jsonImage.optString("size") == "small") {
            image = jsonImage.optString("#text")
            break
        }
    }
    return image
}

private fun JSONArray.getTracksFromArray(): List<Track> {
    val result = mutableListOf<Track>()
    for (index in 0 until this.length()) {
        result.add(
                Track(this.getJSONObject(index).optString("name"))
        )
    }
    return result
}