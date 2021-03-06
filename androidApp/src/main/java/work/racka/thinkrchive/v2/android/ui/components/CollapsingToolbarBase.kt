package work.racka.thinkrchive.v2.android.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import work.racka.thinkrchive.v2.android.R
import work.racka.thinkrchive.v2.android.ui.theme.Dimens
import work.racka.thinkrchive.v2.android.ui.theme.ThinkRchiveTheme

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
    onBackButtonPressed: () -> Unit = { },
    contentAlignment: Alignment = Alignment.Center,
    shape: Shape = RoundedCornerShape(
        bottomEnd = 10.dp,
        bottomStart = 10.dp
    ),
    collapsedBackgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
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
            modifier = Modifier
                .statusBarsPadding(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackButtonPressed,
                modifier = Modifier
                    .padding(Dimens.SmallPadding.size)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_icon),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Text(
                text = toolbarHeading,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                    alpha = animatedTitleAlpha
                ),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(horizontal = Dimens.SmallPadding.size)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
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
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    style = MaterialTheme.typography.headlineMedium,
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
