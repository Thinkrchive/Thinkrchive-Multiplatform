package work.racka.thinkrchive.v2.android.ui.main.screens.settings

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import work.racka.thinkrchive.v2.android.ui.components.CollapsingToolbarBase
import work.racka.thinkrchive.v2.android.ui.components.SettingEntrySheet
import work.racka.thinkrchive.v2.android.ui.components.SettingsEntry
import work.racka.thinkrchive.v2.android.ui.theme.Dimens
import work.racka.thinkrchive.v2.android.ui.theme.Theme
import work.racka.thinkrchive.v2.android.ui.theme.ThinkRchiveTheme
import work.racka.thinkrchive.v2.android.utils.Constants
import work.racka.thinkrchive.v2.android.utils.Sort

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenUI(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    onThemeOptionClicked: (Int) -> Unit = { },
    onSortOptionClicked: (Int) -> Unit = { },
    onBackButtonPressed: () -> Unit = { },
    currentTheme: Int,
    currentSortOption: Int
) {
    // CollapsingToolbar Implementation
    val toolbarHeight = 250.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                // Returning Zero so we just observe the scroll but don't execute it
                return Offset.Zero
            }
        }
    }

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val settingsEntryName = rememberSaveable {
        mutableStateOf("")
    }

    ModalBottomSheetLayout(
        sheetContent = {
            SettingEntrySheet(
                sheetState = sheetState,
                scope = scope,
                settingsEntryName = settingsEntryName.value,
                currentSortOption = currentSortOption,
                currentTheme = currentTheme,
                onSortOptionClicked = {
                    onSortOptionClicked(it)
                },
                onThemeOptionClicked = {
                    onThemeOptionClicked(it)
                }
            )

        },
        sheetState = sheetState,
        sheetElevation = 0.dp,
        sheetBackgroundColor = Color.Transparent
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                CollapsingToolbarBase(
                    toolbarHeading = "Settings",
                    onBackButtonPressed = onBackButtonPressed,
                    toolbarHeight = 250.dp,
                    toolbarOffset = toolbarOffsetHeightPx.value,
                    content = {
                        Text(
                            text = "Settings",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .padding(horizontal = Dimens.SmallPadding.size)
                                .animateContentSize(
                                    animationSpec = tween(
                                        300,
                                        easing = LinearOutSlowInEasing
                                    )
                                )
                        )
                    }
                )
            }
        ) {
            LazyColumn(
                modifier = modifier
                    .nestedScroll(nestedScrollConnection),
                state = listState,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    val theme = when (currentTheme) {
                        1 -> Theme.LIGHT_THEME
                        2 -> Theme.DARK_THEME
                        12 -> Theme.MATERIAL_YOU
                        else -> Theme.FOLLOW_SYSTEM
                    }
                    SettingsEntry(
                        modifier = Modifier.padding(
                            vertical = Dimens.SmallPadding.size,
                            horizontal = Dimens.MediumPadding.size
                        ),
                        settingsEntryName = Constants.THEME_OPTIONS,
                        currentSettingValue = theme.themeName,
                        currentSettingIcon = theme.icon,
                        onSettingsEntryClick = {
                            settingsEntryName.value = it
                            scope.launch {
                                sheetState.show()
                            }
                        }
                    )
                }
                item {
                    val sort = when (currentSortOption) {
                        1 -> Sort.NEW_RELEASE_FIRST
                        2 -> Sort.OLD_RELEASE_FIRST
                        3 -> Sort.LOW_PRICE_FIRST
                        4 -> Sort.HIGH_PRICE_FIRST
                        else -> Sort.ALPHABETICAL_ASC
                    }
                    SettingsEntry(
                        modifier = Modifier.padding(
                            vertical = Dimens.SmallPadding.size,
                            horizontal = Dimens.MediumPadding.size
                        ),
                        settingsEntryName = Constants.SORT_OPTIONS,
                        currentSettingValue = sort.type,
                        currentSettingIcon = sort.icon,
                        onSettingsEntryClick = {
                            settingsEntryName.value = it
                            scope.launch {
                                sheetState.show()
                            }
                        }
                    )
                }
            }
        }

    }
}

@Preview(
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    device = Devices.PIXEL_4
)
@Composable
private fun ThinkpadSettingsScreenPrev() {
    ThinkRchiveTheme {
        SettingsScreenUI(currentTheme = 2, currentSortOption = 1)
    }
}