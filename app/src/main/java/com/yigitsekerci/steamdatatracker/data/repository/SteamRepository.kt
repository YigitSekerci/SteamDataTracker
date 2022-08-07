package com.yigitsekerci.steamdatatracker.data.repository

import com.yigitsekerci.steamdatatracker.domain.model.SteamFeaturedCategories
import com.yigitsekerci.steamdatatracker.util.Resource
import kotlinx.coroutines.flow.Flow

interface SteamRepository {
    suspend fun getFeaturedCategories(
        fromRemote: Boolean
    ): Flow<Resource<SteamFeaturedCategories>>
}