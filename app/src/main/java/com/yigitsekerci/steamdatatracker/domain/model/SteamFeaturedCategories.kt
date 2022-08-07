package com.yigitsekerci.steamdatatracker.domain.model

import com.yigitsekerci.steamdatatracker.data.local.entity.SteamFeaturedCategoriesEntity

data class SteamFeaturedCategories(
    val comingSoon: List<AppInfo>,
    val newReleases: List<AppInfo>,
    val specials: List<AppInfo>,
    val topSellers: List<AppInfo>,
) {
    fun toJointList(): List<List<AppInfo>> {
        return listOf(comingSoon, newReleases, specials, topSellers)
    }

    fun toSteamFeaturedCategoriesEntity() = SteamFeaturedCategoriesEntity(
        comingSoon = comingSoon,
        newReleases = newReleases,
        specials = specials,
        topSellers = topSellers
    )
}
