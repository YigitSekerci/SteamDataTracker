package com.yigitsekerci.steamdatatracker.data.repository

import com.yigitsekerci.steamdatatracker.domain.model.SteamFeaturedCategories
import com.yigitsekerci.steamdatatracker.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSteamRepository : SteamRepository {
    override suspend fun getFeaturedCategories(fromRemote: Boolean): Flow<Resource<SteamFeaturedCategories>> =
        flow {
            emit(Resource.Loading(isLoading = true))
            when (fromRemote) {
                true -> {
                    emit(
                        Resource.Success(
                            data = SteamFeaturedCategories(
                                emptyList(), emptyList(),
                                emptyList(), emptyList()
                            )
                        )
                    )
                }
                false -> {
                    emit(
                        Resource.Error(
                            message = "Fetch error!"
                        )
                    )
                }
            }
            emit(Resource.Loading(isLoading = false))
        }
}