/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.lastfm.data.search

import com.rosberry.android.lastfm.entity.Artist
import okhttp3.ResponseBody
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
        return ArtistsConverter()
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
                        var image = ""
                        for (imageIndex in 0 until jsonImages.length()) {
                            val jsonImage = jsonImages.getJSONObject(imageIndex)
                            if (jsonImage.optString("size") == "small") {
                                image = jsonImage.optString("#text")
                                break
                            }
                        }

                        result.add(Artist(
                                jsonArtist.optString("mbid"),
                                jsonArtist.optString("name"),
                                image
                        ))
                    }
                    return result
                } else {
                    return result
                }
            } else {
                return result
            }
        }

    }
}