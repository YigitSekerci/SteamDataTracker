package com.yigitsekerci.steamdatatracker.ui.appinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.yigitsekerci.steamdatatracker.R

@Composable
fun AppInfoScreen(
    viewModel: AppInfoScreenViewModel = hiltViewModel()
) {
    val appInfo = viewModel.state.appInfo
    val columnSpacing = 8.dp
    val columnPadding = 12.dp
    val scrollState = rememberLazyListState()
    appInfo?.let { info ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(columnPadding),
            verticalArrangement = Arrangement.spacedBy(columnSpacing),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = scrollState,
            userScrollEnabled = true
        ) {
            item {
                val radius = 8.dp
                val shape = RoundedCornerShape(radius)
                val imageHeight = 300.dp
                val imageElevation = 1.dp
                AsyncImage(
                    model = info.large_capsule_image,
                    contentDescription = info.name,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight)
                        .clip(shape)
                        .shadow(elevation = imageElevation)
                )
            }
            item {
                AppInfoText(
                    headerText = stringArrayResource(id = R.array.app_info_headline)[0],
                    description = info.name
                )
            }
            item {
                val finalPrice = appInfo.final_price / 100
                val description = if (appInfo.discounted) {
                    stringResource(
                        id = R.string.app_info_discounted_price_details,
                        finalPrice,
                        appInfo.currency,
                        appInfo.discount_percent
                    )
                } else {
                    stringResource(
                        id = R.string.app_info_price_details,
                        finalPrice,
                        appInfo.currency,
                    )
                }
                AppInfoText(
                    headerText = stringArrayResource(id = R.array.app_info_headline)[1],
                    description = description
                )
            }
        }
    }
}

@Composable
private fun AppInfoText(
    headerText: String,
    description: String,
) {
    val headerFontSize = 20.sp
    val descriptionFontSize = 16.sp
    val columnSpace = 8.dp
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = headerText,
            fontWeight = FontWeight.Bold,
            fontSize = headerFontSize,
            overflow = TextOverflow.Ellipsis
        )
        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(columnSpace))
        Text(
            text = description,
            fontWeight = FontWeight.Normal,
            fontSize = descriptionFontSize
        )
    }
}