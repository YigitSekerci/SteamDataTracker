package com.yigitsekerci.steamdatatracker.ui.featuredcategory

import com.yigitsekerci.steamdatatracker.data.repository.FakeSteamRepository
import com.yigitsekerci.steamdatatracker.domain.model.SteamFeaturedCategories
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FeaturedCategoryScreenViewModelTest {
    private lateinit var featuredCategoryScreenViewModel: FeaturedCategoryScreenViewModel

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        featuredCategoryScreenViewModel = FeaturedCategoryScreenViewModel(FakeSteamRepository())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `get categories from remote, returns data in state`() {
        runBlocking {
            launch(Dispatchers.Main) {
                featuredCategoryScreenViewModel.getFeaturedCategories(fromRemote = true)
            }
            delay(1000L)
        }
        assertEquals(
            SteamFeaturedCategories(
                emptyList(), emptyList(),
                emptyList(), emptyList()
            ),
            featuredCategoryScreenViewModel.state.featuredCategories,
        )
    }

    @Test
    fun `get categories from local, returns error in state`() {
        runBlocking {
            launch(Dispatchers.Main) {
                featuredCategoryScreenViewModel.getFeaturedCategories(fromRemote = false)
            }
        }
        assertEquals(
            "Fetch error!",
            featuredCategoryScreenViewModel.state.error,
        )
    }
}