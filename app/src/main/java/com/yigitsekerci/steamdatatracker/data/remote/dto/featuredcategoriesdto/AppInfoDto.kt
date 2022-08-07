package com.yigitsekerci.steamdatatracker.data.remote.dto.featuredcategoriesdto

import com.squareup.moshi.Json
import com.yigitsekerci.steamdatatracker.domain.model.AppInfo

data class AppInfoDto(
    @field:Json(name = "currency") val currency: String,
    @field:Json(name = "discount_expiration") val discount_expiration: Int,
    @field:Json(name = "discount_percent") val discount_percent: Int,
    @field:Json(name = "discounted") val discounted: Boolean,
    @field:Json(name = "final_price") val final_price: Int,
    @field:Json(name = "header_image") val header_image: String,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "large_capsule_image") val large_capsule_image: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "small_capsule_image") val small_capsule_image: String,
) {
    fun toAppInfo() = AppInfo(
        currency,
        discount_expiration,
        discount_percent,
        discounted,
        final_price,
        header_image,
        id,
        large_capsule_image,
        name,
        small_capsule_image
    )
}
