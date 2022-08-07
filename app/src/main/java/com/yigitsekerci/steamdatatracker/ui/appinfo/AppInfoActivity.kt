package com.yigitsekerci.steamdatatracker.ui.appinfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yigitsekerci.steamdatatracker.domain.model.AppInfo
import com.yigitsekerci.steamdatatracker.ui.theme.SteamDataTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppInfoActivity : ComponentActivity() {
    companion object {
        const val APP_INFO_KEY = "app_info_key"

        fun startActivity(appInfo: AppInfo, context: Context) {
            Intent(context, AppInfoActivity::class.java).apply {
                putExtra(APP_INFO_KEY, appInfo)
                context.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SteamDataTrackerTheme {
                AppInfoScreen()
            }
        }
    }
}