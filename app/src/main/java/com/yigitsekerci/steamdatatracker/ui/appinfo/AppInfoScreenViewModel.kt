package com.yigitsekerci.steamdatatracker.ui.appinfo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yigitsekerci.steamdatatracker.domain.model.AppInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppInfoScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(AppInfoScreenState())

    init {
        val appInfo = savedStateHandle.get<AppInfo>(AppInfoActivity.APP_INFO_KEY)
        state = state.copy(appInfo = appInfo)
    }
}