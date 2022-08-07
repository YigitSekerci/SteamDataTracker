package com.yigitsekerci.steamdatatracker.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET

interface SteamApi {
    companion object {
        const val BASE_URL = "https://store.steampowered.com/api/"
    }

    @GET("featuredcategories/")
    suspend fun getCategories(): ResponseBody
}