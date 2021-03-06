package work.racka.thinkrchive.v2.desktop.ui.screens.donate

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import domain.Product
import work.racka.thinkrchive.v2.desktop.ui.components.CollapsingToolbarBase
import work.racka.thinkrchive.v2.desktop.ui.components.qonversion.BillingEntries
import work.racka.thinkrchive.v2.desktop.ui.theme.Dimens

@Composable
fun DonateScreenUI(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    products: List<Product>,
    onBackButtonPressed: () -> Unit = { },
    onProductClicked: (Int) -> Unit = { }
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CollapsingToolbarBase(
                toolbarHeading = "Donate",
                toolbarHeight = toolbarHeight,
                toolbarOffset = toolbarOffsetHeightPx.value,
                onCloseButtonClicked = onBackButtonPressed
            ) {
                Text(
                    text = "Donate",
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.h3,
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
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(top = Dimens.MediumPadding.size)
                .nestedScroll(nestedScrollConnection),
            state = listState
        ) {

            BillingEntries(
                products = products,
                hasPremium = false,
                onProductClick = {
                    onProductClicked(it)
                }
            )
        }

    }

}