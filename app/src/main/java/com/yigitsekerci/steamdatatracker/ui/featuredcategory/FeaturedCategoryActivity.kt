package com.yigitsekerci.steamdatatracker.ui.featuredcategory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yigitsekerci.steamdatatracker.ui.theme.SteamDataTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeaturedCategoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SteamDataTrackerTheme {
                FeaturedCategoryScreen()
            }
        }
    }
}