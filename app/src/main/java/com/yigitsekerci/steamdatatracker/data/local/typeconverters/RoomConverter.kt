package com.yigitsekerci.steamdatatracker.data.local.typeconverters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.yigitsekerci.steamdatatracker.domain.model.AppInfo
import java.lang.reflect.Type

@ProvidedTypeConverter
class RoomConverter(private val moshi: Moshi) {
    @TypeConverter
    fun fromAppInfoList(data: List<AppInfo>): String {
        val type: Type = Types.newParameterizedType(
            MutableList::class.java,
            AppInfo::class.java
        )
        return moshi.adapter<List<AppInfo>>(type).toJson(data)
    }

    @TypeConverter
    fun toAppInfoList(json: String): List<AppInfo> {
        val type: Type = Types.newParameterizedType(
            MutableList::class.java,
            AppInfo::class.java
        )
        return moshi.adapter<List<AppInfo>>(type).fromJson(json) ?: emptyList()
    }
}