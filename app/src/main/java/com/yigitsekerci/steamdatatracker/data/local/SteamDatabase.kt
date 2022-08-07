package com.yigitsekerci.steamdatatracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yigitsekerci.steamdatatracker.data.local.entity.SteamFeaturedCategoriesEntity
import com.yigitsekerci.steamdatatracker.data.local.typeconverters.RoomConverter

@Database(entities = [SteamFeaturedCategoriesEntity::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverter::class)
abstract class SteamDatabase : RoomDatabase() {
    abstract val dao: SteamDao
}