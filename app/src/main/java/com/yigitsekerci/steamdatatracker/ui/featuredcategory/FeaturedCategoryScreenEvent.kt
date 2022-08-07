package com.yigitsekerci.steamdatatracker.ui.featuredcategory

sealed class FeaturedCategoryScreenEvent {
    object SwipeRefresh : FeaturedCategoryScreenEvent()
}