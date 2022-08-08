package com.yigitsekerci.steamdatatracker.ui.featuredcategory

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yigitsekerci.steamdatatracker.data.repository.SteamRepository
import com.yigitsekerci.steamdatatracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@HiltViewModel
class FeaturedCategoryScreenViewModel @Inject constructor(
    private val repository: SteamRepository
) : ViewModel() {
    var state by mutableStateOf(FeaturedCategoryScreenState())
    var searchJob: Job? = null

    init {
        getFeaturedCategories()
    }

    fun onEvent(event: FeaturedCategoryScreenEvent) {
        when (event) {
            is FeaturedCategoryScreenEvent.SwipeRefresh -> {
                getFeaturedCategories(fromRemote = true)
            }
        }
    }

    fun getFeaturedCategories(fromRemote: Boolean = false) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository
                .getFeaturedCategories(fromRemote)
                .collect { resource ->
                    state = when (resource) {
                        is Resource.Error -> {
                            state.copy(error = resource.message)
                        }
                        is Resource.Loading -> {
                            state.copy(isLoading = resource.isLoading)
                        }
                        is Resource.Success -> {
                            state.copy(featuredCategories = resource.data)
                        }
                    }
                }
        }
    }
}