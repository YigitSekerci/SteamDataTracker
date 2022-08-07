package com.yigitsekerci.steamdatatracker.data.remote.dto.featuredcategoriesdto

import com.squareup.moshi.Json
import com.yigitsekerci.steamdatatracker.domain.model.SteamFeaturedCategories

data class SteamFeaturedCategoriesDto(
    @field:Json(name = "coming_soon") val coming_soon: CategoryInfoDto,
    @field:Json(name = "new_releases") val new_releases: CategoryInfoDto,
    @field:Json(name = "specials") val specials: CategoryInfoDto,
    @field:Json(name = "top_sellers") val top_sellers: CategoryInfoDto,
){
    fun toSteamFeaturedCategories() = SteamFeaturedCategories(
        comingSoon = coming_soon.items.map { it.toAppInfo() },
        newReleases = new_releases.items.map { it.toAppInfo() },
        specials = specials.items.map { it.toAppInfo() },
        topSellers = top_sellers.items.map { it.toAppInfo() }
    )
}