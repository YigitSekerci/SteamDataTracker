package com.yigitsekerci.steamdatatracker.data.parser

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.yigitsekerci.steamdatatracker.data.remote.dto.featuredcategoriesdto.SteamFeaturedCategoriesDto
import com.yigitsekerci.steamdatatracker.domain.model.SteamFeaturedCategories
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SteamFeaturedCategoriesParser @Inject constructor(
    moshi: Moshi
) : JsonParser<SteamFeaturedCategories> {
    private val adapter: JsonAdapter<SteamFeaturedCategoriesDto> =
        moshi.adapter(SteamFeaturedCategoriesDto::class.java)

    override suspend fun parse(jsonString: String): SteamFeaturedCategories? {
        return adapter.fromJson(jsonString)?.toSteamFeaturedCategories()
    }
}