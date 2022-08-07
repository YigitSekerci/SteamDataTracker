package com.yigitsekerci.steamdatatracker.ui.featuredcategory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.yigitsekerci.steamdatatracker.R
import com.yigitsekerci.steamdatatracker.domain.model.AppInfo
import com.yigitsekerci.steamdatatracker.ui.appinfo.AppInfoActivity

@Composable
fun FeaturedCategoryScreen() {
    val columnSpacing = 8.dp
    val columnPadding = 8.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(columnPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(columnSpacing)
    ) {
        AppNameComp()
        SteamFeaturedCategoryComp()
    }
}

@Composable
private fun AppNameComp() {
    val fontSize = 24.sp
    Text(
        text = stringResource(id = R.string.app_name),
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun SteamFeaturedCategoryComp(
    viewModel: FeaturedCategoryScreenViewModel = hiltViewModel()
) {
    val refreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isLoading)
    viewModel.state.featuredCategories?.let { featuredCategories ->
        val categories = featuredCategories.toJointList()
        val columnSpacing = 24.dp
        val scrollState = rememberLazyListState()
        SwipeRefresh(
            state = refreshState,
            onRefresh = {
                viewModel.onEvent(FeaturedCategoryScreenEvent.SwipeRefresh)
            },
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(columnSpacing),
                state = scrollState
            ) {
                itemsIndexed(categories) { index, item ->
                    CategoryColumn(
                        category = stringArrayResource(id = R.array.categories)[index],
                        appInfos = item
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryColumn(
    category: String,
    appInfos: List<AppInfo>
) {
    val fontSize = 24.sp
    val columnSpacing = 10.dp
    val cardSpacing = 12.dp
    val rowSize = 2
    val chunkedAppInfos = appInfos.chunked(rowSize)
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = category,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize
        )
        Divider()
        Spacer(modifier = Modifier.height(columnSpacing))
        chunkedAppInfos.forEachIndexed { index, list ->
            AppInfoRow(appInfos = list)
            if (index != chunkedAppInfos.lastIndex) {
                Spacer(modifier = Modifier.height(cardSpacing))
            }
        }
    }
}

@Composable
private fun AppInfoRow(
    appInfos: List<AppInfo>
) {
    val rowSpacing = 12.dp
    val wantedRowSize = 2
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(rowSpacing)
    ) {
        appInfos.forEach {
            AppInfoCard(appInfo = it)
        }
        if (appInfos.size != wantedRowSize) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun RowScope.AppInfoCard(
    appInfo: AppInfo
) {
    val context = LocalContext.current
    val elevation = 8.dp
    val radius = 8.dp
    val roundedCornerShape = RoundedCornerShape(radius)
    val cardHeight = 120.dp
    val backgroundBrush =
        Brush.verticalGradient(listOf(Color.Transparent, Color.White.copy(alpha = 0.6f)))
    Box(
        modifier = Modifier
            .weight(1f)
            .height(cardHeight)
            .shadow(elevation, roundedCornerShape)
            .clip(roundedCornerShape)
            .clickable {
                AppInfoActivity.startActivity(context = context, appInfo = appInfo)
            },
    ) {
        AsyncImage(
            model = appInfo.large_capsule_image,
            contentDescription = appInfo.name,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
        )
        Text(
            text = appInfo.name,
            Modifier.align(Alignment.BottomCenter),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold
        )
    }
}