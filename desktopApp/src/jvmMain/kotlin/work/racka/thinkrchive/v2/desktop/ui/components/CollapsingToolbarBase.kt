package work.racka.thinkrchive.v2.desktop.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import work.racka.thinkrchive.v2.desktop.ui.theme.Dimens
import work.racka.thinkrchive.v2.desktop.ui.theme.ThinkRchiveTheme
import work.racka.thinkrchive.v2.desktop.utils.StringResource

/**
 * Collapsing Toolbar that can be used in a topBar slot of Scaffold.
 * It has a back button, default bottom rounded corners
 * & a box scope which holds content centered by default.
 * You need to implement nestedScrollConnection to set the offset values
 * See Usage of this in AboutScreen or SettingsScreen or DetailsScreen
 *
 * To use this Toolbar with a persistent Image that's always visible
 * and just shrinks when scroll then leave the toolbarHeading blank("")
 *
 * With nestedScrollConnection know that the maximum offset that can be
 * reached is -132.0
 */
@Composable
fun CollapsingToolbarBase(
    modifier: Modifier = Modifier,
    toolbarHeading: String,
    onCloseButtonClicked: () -> Unit = { },
    contentAlignment: Alignment = Alignment.Center,
    shape: Shape = RoundedCornerShape(
        bottomEnd = 10.dp,
        bottomStart = 10.dp
    ),
    collapsedBackgroundColor: Color = MaterialTheme.colors.surface,
    backgroundColor: Color = MaterialTheme.colors.background,
    toolbarHeight: Dp,
    minShrinkHeight: Dp = 100.dp,
    toolbarOffset: Float,
    content: @Composable BoxScope.() -> Unit
) {
    //Scale animation
    val animatedProgress = remember {
        Animatable(initialValue = 0.9f)
    }

    val scrollDp = toolbarHeight + toolbarOffset.dp
    val animatedCardSize by animateDpAsState(
        targetValue = if (scrollDp <= minShrinkHeight) minShrinkHeight else scrollDp,
        animationSpec = tween(300, easing = LinearOutSlowInEasing)
    )
    val animatedElevation by animateDpAsState(
        targetValue = if (scrollDp < minShrinkHeight + 20.dp) 10.dp else 0.dp,
        animationSpec = tween(500, easing = LinearOutSlowInEasing)
    )
    val animatedTitleAlpha by animateFloatAsState(
        targetValue = if (toolbarHeading.isNotBlank()) {
            if (scrollDp <= minShrinkHeight + 20.dp) 1f else 0f
        } else 0f,
        animationSpec = tween(300, easing = LinearOutSlowInEasing)
    )
    val animatedColor by animateColorAsState(
        targetValue = if (scrollDp < minShrinkHeight + 20.dp) collapsedBackgroundColor
        else backgroundColor,
        animationSpec = tween(300, easing = LinearOutSlowInEasing)
    )

    LaunchedEffect(key1 = animatedProgress) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(500, easing = FastOutSlowInEasing)
        )
    }

    val animatedModifier = modifier
        .graphicsLayer(
            scaleX = animatedProgress.value
        )

    Box(
        modifier = animatedModifier
            .fillMaxWidth()
            .height(animatedCardSize)
            .shadow(
                elevation = animatedElevation,
                shape = shape
            )
            .background(
                color = animatedColor,
                shape = shape
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onCloseButtonClicked,
                modifier = Modifier
                    .padding(Dimens.SmallPadding.size)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = StringResource.close_icon,
                    tint = MaterialTheme.colors.onSurface
                )
            }
            Text(
                text = toolbarHeading,
                color = MaterialTheme.colors.onSurface.copy(
                    alpha = animatedTitleAlpha
                ),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(horizontal = Dimens.SmallPadding.size)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(1 - animatedTitleAlpha),
            contentAlignment = contentAlignment,
            content = content
        )
    }
}

@Preview
@Composable
fun CollapsingToolbarPrev() {
    ThinkRchiveTheme {
        CollapsingToolbarBase(
            toolbarHeading = "Settings",
            toolbarOffset = 0f,
            toolbarHeight = 300.dp,
            content = {
                Text(
                    text = "Settings",
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
        )
    }
}
