package com.yigitsekerci.steamdatatracker.data.remote.dto.featuredcategoriesdto

data class CategoryInfoDto(
    val id: String,
    val items: List<AppInfoDto>,
    val name: String
)