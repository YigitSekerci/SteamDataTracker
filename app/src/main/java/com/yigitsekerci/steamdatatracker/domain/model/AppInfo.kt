package com.yigitsekerci.steamdatatracker.domain.model

import java.io.Serializable

data class AppInfo(
    val currency: String,
    val discount_expiration: Int,
    val discount_percent: Int,
    val discounted: Boolean,
    val final_price: Int,
    val header_image: String,
    val id: Int,
    val large_capsule_image: String,
    val name: String,
    val small_capsule_image: String,
) : Serializable