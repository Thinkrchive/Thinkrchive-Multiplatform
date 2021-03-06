package work.racka.thinkrchive.v2.android.ui.components

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import domain.Thinkpad
import work.racka.thinkrchive.v2.android.R
import work.racka.thinkrchive.v2.android.ui.theme.BlueLinkColor
import work.racka.thinkrchive.v2.android.ui.theme.Dimens
import work.racka.thinkrchive.v2.android.ui.theme.Shapes
import work.racka.thinkrchive.v2.android.ui.theme.ThinkRchiveTheme
import work.racka.thinkrchive.v2.android.utils.Constants

@Composable
fun ThinkpadDetails(
    modifier: Modifier = Modifier,
    thinkpad: Thinkpad,
    onExternalLinkClick: () -> Unit = { }
) {
    //Scale animation
    val animatedProgress = remember {
        Animatable(initialValue = 0.7f)
    }
    LaunchedEffect(key1 = Unit) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(500, easing = FastOutSlowInEasing)
        )
    }

    val animatedModifier = modifier
        .graphicsLayer(
            scaleX = animatedProgress.value,
            scaleY = animatedProgress.value
        )

    val divider = " - "
    val marketPrice by remember(thinkpad) {
        derivedStateOf {
            buildAnnotatedString {
                append("$${thinkpad.marketPriceStart}")
                append(divider)
                append("$${thinkpad.marketPriceEnd}")
            }.text
        }
    }

    var maxLines by remember {
        mutableStateOf(1)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180F else 0F,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

    Column(
        modifier = animatedModifier
            .fillMaxWidth()
            .padding(Dimens.MediumPadding.size)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = thinkpad.model,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(horizontal = Dimens.MediumPadding.size)
                    .padding(bottom = Dimens.MediumPadding.size)
            )
            IconButton(
                onClick = onExternalLinkClick,
                modifier = Modifier
                    .padding(horizontal = Dimens.SmallPadding.size)
                    .padding(bottom = Dimens.MediumPadding.size)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Launch,
                    contentDescription = stringResource(id = R.string.external_link),
                    tint = BlueLinkColor
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = Shapes.large
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                ),
            contentAlignment = Alignment.CenterStart
        ) {

            Column(
                modifier = Modifier
                    .padding(Dimens.MediumPadding.size)
            ) {
                SubtitleTextWithIcon(
                    subtitleName = "Series",
                    subtitleData = thinkpad.series,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.Category,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Release Date",
                    subtitleData = thinkpad.releaseDate,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.Today,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Market Value",
                    subtitleData = marketPrice,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.Loyalty,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Platform",
                    subtitleData = thinkpad.processorPlatforms,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.DeveloperBoard,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Processors",
                    subtitleData = thinkpad.processors,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.Memory,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Graphics",
                    subtitleData = thinkpad.graphics,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.Dvr,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Display Res",
                    subtitleData = thinkpad.displayRes,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.Laptop,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Max RAM",
                    subtitleData = thinkpad.maxRam,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.ViewAgenda,
                    maxLines = maxLines
                )
            }

            IconButton(
                onClick = {
                    maxLines = if (maxLines == 1) {
                        expanded = true
                        Int.MAX_VALUE
                    } else {
                        expanded = false
                        1
                    }
                },
                modifier = Modifier
                    .padding(
                        top = Dimens.SmallPadding.size,
                        end = Dimens.SmallPadding.size
                    )
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ExpandMore,
                    contentDescription = stringResource(id = R.string.expand_icon),
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.rotate(angle)
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun ThinkpadFeaturesPreview() {
    ThinkRchiveTheme {
        ThinkpadDetails(thinkpad = Constants.ThinkpadsListPreview[0])
    }
}

@Composable
fun SubtitleTextWithIcon(
    subtitleName: String,
    subtitleData: String,
    style: TextStyle = MaterialTheme.typography.titleLarge,
    maxLines: Int = 1,
    iconDescription: String? = null,
    icon: ImageVector
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(vertical = Dimens.SmallPadding.size)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Spacer(modifier = Modifier.width(Dimens.MediumPadding.size))
        SubtitleText(
            verticalPadding = 0.dp,
            subtitleName = subtitleName,
            subtitleData = subtitleData,
            style = style,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            maxLines = maxLines,
            verticalAlignment = Alignment.Top
        )
    }
}
