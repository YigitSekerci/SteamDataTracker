package com.yigitsekerci.steamdatatracker.di

import com.yigitsekerci.steamdatatracker.data.parser.JsonParser
import com.yigitsekerci.steamdatatracker.data.parser.SteamFeaturedCategoriesParser
import com.yigitsekerci.steamdatatracker.data.repository.SteamRepository
import com.yigitsekerci.steamdatatracker.data.repository.SteamRepositoryImpl
import com.yigitsekerci.steamdatatracker.domain.model.SteamFeaturedCategories
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFeaturedCategoryParser(
        featuredCategoriesParser: SteamFeaturedCategoriesParser
    ): JsonParser<SteamFeaturedCategories>

    @Binds
    @Singleton
    abstract fun bindSteamRepository(
        steamRepositoryImpl: SteamRepositoryImpl
    ): SteamRepository
}