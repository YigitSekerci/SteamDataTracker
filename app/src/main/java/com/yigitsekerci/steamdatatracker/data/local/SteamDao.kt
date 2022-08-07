package com.yigitsekerci.steamdatatracker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yigitsekerci.steamdatatracker.data.local.entity.SteamFeaturedCategoriesEntity

@Dao
interface SteamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeaturedCategories(entity: SteamFeaturedCategoriesEntity)

    @Query("DELETE FROM SteamFeaturedCategoriesEntity")
    suspend fun clearFeaturedCategories()

    @Query("SELECT * FROM SteamFeaturedCategoriesEntity")
    suspend fun getFeaturedCategories(): SteamFeaturedCategoriesEntity?
}