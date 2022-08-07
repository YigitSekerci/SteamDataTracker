package com.yigitsekerci.steamdatatracker.data.repository

import com.yigitsekerci.steamdatatracker.data.local.SteamDatabase
import com.yigitsekerci.steamdatatracker.data.parser.JsonParser
import com.yigitsekerci.steamdatatracker.data.remote.SteamApi
import com.yigitsekerci.steamdatatracker.domain.model.SteamFeaturedCategories
import com.yigitsekerci.steamdatatracker.util.Resource
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber

@Singleton
class SteamRepositoryImpl @Inject constructor(
    private val api: SteamApi,
    private val parser: JsonParser<SteamFeaturedCategories>,
    database: SteamDatabase,
) : SteamRepository {
    private val dao = database.dao

    override suspend fun getFeaturedCategories(fromRemote: Boolean): Flow<Resource<SteamFeaturedCategories>> =
        flow {
            emit(Resource.Loading(isLoading = true))
            val localCategories = dao.getFeaturedCategories()
            localCategories?.let {
                emit(Resource.Success(data = it.toSteamFeaturedCategories()))
            }
            val shouldFetchFromRemote = localCategories == null || fromRemote

            if (!shouldFetchFromRemote) {
                emit(Resource.Loading(isLoading = false))
                return@flow
            }

            val remoteCategories = try {
                val response = api.getCategories()
                parser.parse(response.string())
            } catch (ioException: IOException) {
                emit(Resource.Error(message = ioException.message))
                null
            } catch (httpException: HttpException) {
                emit(Resource.Error(message = httpException.message))
                null
            }

            remoteCategories?.let { categories ->
                Timber.d("${categories.topSellers}")
                dao.clearFeaturedCategories()
                dao.insertFeaturedCategories(categories.toSteamFeaturedCategoriesEntity())
                emit(Resource.Success(dao.getFeaturedCategories()!!.toSteamFeaturedCategories()))
                emit(Resource.Loading(isLoading = false))
            }
        }
}