package com.yigitsekerci.steamdatatracker.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.yigitsekerci.steamdatatracker.data.local.SteamDatabase
import com.yigitsekerci.steamdatatracker.data.local.typeconverters.RoomConverter
import com.yigitsekerci.steamdatatracker.data.remote.SteamApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSteamApi(): SteamApi {
        return Retrofit.Builder()
            .baseUrl(SteamApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
            )
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSteamDatabase(
        app: Application,
        moshi: Moshi
    ): SteamDatabase {
        return Room.databaseBuilder(
            app,
            SteamDatabase::class.java,
            "steamdatabase.db"
        )
            .addTypeConverter(RoomConverter(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}