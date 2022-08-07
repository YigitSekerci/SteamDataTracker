package com.yigitsekerci.steamdatatracker.ui.featuredcategory

import com.yigitsekerci.steamdatatracker.domain.model.SteamFeaturedCategories

data class FeaturedCategoryScreenState(
    val featuredCategories: SteamFeaturedCategories? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)