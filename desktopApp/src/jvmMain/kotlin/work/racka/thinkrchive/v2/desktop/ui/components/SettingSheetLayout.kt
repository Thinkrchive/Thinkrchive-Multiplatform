package work.racka.thinkrchive.v2.desktop.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import work.racka.thinkrchive.v2.android.utils.Sort
import work.racka.thinkrchive.v2.desktop.ui.theme.*
import work.racka.thinkrchive.v2.desktop.utils.Constants

@ExperimentalMaterialApi
@Composable
fun SettingSheetLayout(
    modifier: Modifier = Modifier,
    sheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    sheetTitle: String,
    content: @Composable () -> Unit
) {

    Surface(
        shape = BottomSheetShape,
        color = MaterialTheme.colors.surface,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.SmallPadding.size)
        ) {
            TopSheetSection(
                modifier = Modifier
                    .padding(Dimens.SmallPadding.size),
                sheetTitle = sheetTitle,
                onCloseClicked = {
                    scope.launch {
                        sheetState.hide()
                    }
                },
                settingsButtonVisible = false
            )
            content()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SettingEntrySheet(
    modifier: Modifier = Modifier,
    sheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    settingsEntryName: String,
    currentSortOption: Int,
    onSortOptionClicked: (Int) -> Unit = { },
    currentTheme: Int,
    onThemeOptionClicked: (Int) -> Unit = { }
) {

    SettingSheetLayout(
        modifier = modifier,
        sheetState = sheetState,
        scope = scope,
        sheetTitle = settingsEntryName
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (settingsEntryName == Constants.THEME_OPTIONS) {
                items(Theme.values()) { item ->
                    val selectedColor by animateColorAsState(
                        targetValue = if (currentTheme == item.themeValue) {
                            MaterialTheme.colors.primary
                                .copy(alpha = .6f)
                        } else Color.Transparent,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = LinearOutSlowInEasing
                        )
                    )
                    val contentColor by animateColorAsState(
                        targetValue = if (currentTheme == item.themeValue) {
                            LightDark
                                .copy(alpha = .9f)
                        } else MaterialTheme.colors.onBackground,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = LinearOutSlowInEasing
                        )
                    )

                    SheetOption(
                        modifier = Modifier
                            .fillMaxWidth(),
                        sortOptionName = item.themeName,
                        icon = item.icon,
                        onOptionClicked = {
                            onThemeOptionClicked(item.themeValue)
                        },
                        selectedSortColor = selectedColor,
                        contentColor = contentColor
                    )
                }
            } else if (settingsEntryName == Constants.SORT_OPTIONS) {
                items(Sort.values()) { item ->
                    val selectedColor by animateColorAsState(
                        targetValue = if (currentSortOption == item.sortValue) {
                            MaterialTheme.colors.primary
                                .copy(alpha = .6f)
                        } else Color.Transparent,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = LinearOutSlowInEasing
                        )
                    )
                    val contentColor by animateColorAsState(
                        targetValue = if (currentSortOption == item.sortValue) {
                            LightDark
                                .copy(alpha = .9f)
                        } else MaterialTheme.colors.onBackground,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = LinearOutSlowInEasing
                        )
                    )

                    SheetOption(
                        modifier = Modifier
                            .fillMaxWidth(),
                        sortOptionName = item.type,
                        icon = item.icon,
                        onOptionClicked = {
                            onSortOptionClicked(item.sortValue)
                        },
                        selectedSortColor = selectedColor,
                        contentColor = contentColor
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
private fun SettingsEntrySheetPrev() {
    ThinkRchiveTheme {
        SettingEntrySheet(
            sheetState = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.HalfExpanded
            ),
            scope = rememberCoroutineScope(),
            settingsEntryName = Constants.SORT_OPTIONS,
            currentSortOption = 3,
            currentTheme = 1
        )
    }
}