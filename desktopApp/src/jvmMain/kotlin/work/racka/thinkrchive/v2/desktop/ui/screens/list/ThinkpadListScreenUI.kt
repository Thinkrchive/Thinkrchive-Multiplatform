package work.racka.thinkrchive.v2.desktop.ui.screens.list

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.rememberDialogState
import domain.Thinkpad
import work.racka.thinkrchive.v2.desktop.ui.components.CustomSearchBar
import work.racka.thinkrchive.v2.desktop.ui.components.HomeBottomSheet
import work.racka.thinkrchive.v2.desktop.ui.components.ScrollToTopButton
import work.racka.thinkrchive.v2.desktop.ui.components.ThinkpadEntry
import work.racka.thinkrchive.v2.desktop.ui.theme.Dimens
import work.racka.thinkrchive.v2.desktop.ui.theme.ThinkRchiveTheme
import work.racka.thinkrchive.v2.desktop.utils.Constants
import java.awt.Dimension


@OptIn(ExperimentalMaterialApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Composable
fun ThinkpadListScreenUI(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    onEntryClick: (Thinkpad) -> Unit = { },
    onSearch: (String) -> Unit = { },
    onSortOptionClicked: (Int) -> Unit = { },
    onSettingsClicked: () -> Unit = { },
    onAboutClicked: () -> Unit = { },
    onDonateClicked: () -> Unit = { },
    currentSortOption: Int,
    thinkpadList: List<Thinkpad>,
    networkLoading: Boolean,
    networkError: String
) {

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val openDialog = remember { mutableStateOf(false) }
    val dialogState = rememberDialogState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Dialog(
            onCloseRequest = {
                openDialog.value = false
            },
            state = dialogState,
            visible = openDialog.value,
            resizable = false,
            undecorated = true,
            transparent = true
        ) {
            window.size = Dimension(400, 450)
            HomeBottomSheet(
                sheetState = sheetState,
                scope = scope,
                close = { openDialog.value = false },
                onSortOptionClicked = {
                    onSortOptionClicked(it)
                },
                currentSortOption = currentSortOption,
                onSettingsClicked = onSettingsClicked,
                onDonateClicked = onDonateClicked,
                onAboutClicked = onAboutClicked
            )
        }
        LazyColumn(
            state = listState,
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .padding(top = Dimens.MediumPadding.size)
                )
                CustomSearchBar(
                    focusManager = focusManager,
                    onSearch = {
                        onSearch(it)
                    },
                    onDismissSearchClicked = {
                        onSearch("")
                    },
                    onOptionsClicked = {
                        openDialog.value = true
                    },
                    modifier = Modifier.padding(
                        vertical = Dimens.SmallPadding.size
                    )
                )
            }
            items(thinkpadList) {
                ThinkpadEntry(
                    thinkpad = it,
                    onEntryClick = { onEntryClick(it) },
                    modifier = Modifier
                        .padding(
                            horizontal = Dimens.MediumPadding.size,
                            vertical = Dimens.SmallPadding.size
                        )
                )
            }
            item {
                if (networkLoading) {
                    CircularProgressIndicator()
                }
            }
            item {
                if (networkError.isNotBlank()) {
                    Text(
                        text = networkError,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // This must always stay at the bottom for navBar padding
            item {
                Spacer(modifier = Modifier.padding(Dimens.MediumPadding.size))
            }
        }
        ScrollToTopButton(
            listState = listState,
            modifier = Modifier.fillMaxWidth(),
            scope = scope
        )
    }
}

@Preview
@Composable
private fun ThinkpadListScreenPreview() {
    ThinkRchiveTheme {
        ThinkpadListScreenUI(
            thinkpadList = Constants.ThinkpadsListPreview,
            networkLoading = false,
            networkError = "",
            currentSortOption = 0
        )
    }
}
