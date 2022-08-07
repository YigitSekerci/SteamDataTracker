package com.yigitsekerci.steamdatatracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yigitsekerci.steamdatatracker.domain.model.AppInfo
import com.yigitsekerci.steamdatatracker.domain.model.SteamFeaturedCategories

@Entity
data class SteamFeaturedCategoriesEntity(
    val comingSoon: List<AppInfo>,
    val newReleases: List<AppInfo>,
    val specials: List<AppInfo>,
    val topSellers: List<AppInfo>,
    @PrimaryKey val id: Int? = null
) {
    fun toSteamFeaturedCategories() = SteamFeaturedCategories(
        comingSoon = comingSoon,
        newReleases = newReleases,
        specials = specials,
        topSellers = topSellers
    )
}