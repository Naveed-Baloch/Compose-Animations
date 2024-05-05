package com.naveed.composeanimations.animateddrawer

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.naveed.composeanimations.DrawerState

@Composable
fun AnimatedDrawer(
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
    shouldRotate: Boolean = true,
    animateDuration: Int = 500,
    defaultTargetOffsetX: Dp = 250.dp,
    defaultTargetOffsetY: Dp = 120.dp,
    backgroundColors: List<Color> = emptyList(),
    drawerContent: @Composable BoxScope.() -> Unit,
    homeContent: @Composable BoxScope.() -> Unit,
) {
    val isOpened = drawerState == DrawerState.OPENED
    val roundedCorners = animateDpAsState(targetValue = if (isOpened) 30.dp else 0.dp, label = "", animationSpec = tween(if (isOpened) animateDuration else 1000))
    val rotationAngle = animateFloatAsState(targetValue = if (isOpened && shouldRotate) -10f else 0f, label = "", animationSpec = tween(animateDuration))
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        drawerContent()

        (backgroundColors).forEachIndexed { index, color ->
            val targetXOffset = if (isOpened) defaultTargetOffsetX + (index * 10).dp else 0.dp
            val targetYOffset = if (isOpened) defaultTargetOffsetY - (index * 10).dp else 0.dp
            val backgroundOffsetX = animateDpAsState(targetValue = targetXOffset, label = "", animationSpec = tween(animateDuration))
            val backgroundOffsetY = animateDpAsState(targetValue = targetYOffset, label = "", animationSpec = tween(animateDuration))
            Box(
                modifier = Modifier
                    .offset(x = backgroundOffsetX.value, y = backgroundOffsetY.value)
                    .rotate(rotationAngle.value)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(roundedCorners.value))
                    .background(color)
            )
        }

        val contentTargetOffsetX = if (isOpened) defaultTargetOffsetX + (backgroundColors.size * 10).dp else 0.dp
        val contentTargetOffsetY = if (isOpened) defaultTargetOffsetY - (backgroundColors.size * 10).dp else 0.dp
        val contentOffsetX = animateDpAsState(targetValue = contentTargetOffsetX, label = "", animationSpec = tween(animateDuration))
        val contentOffsetY = animateDpAsState(targetValue = contentTargetOffsetY, label = "", animationSpec = tween(animateDuration))
        Box(
            modifier = Modifier
                .offset(x = contentOffsetX.value, y = contentOffsetY.value)
                .rotate(rotationAngle.value)
                .fillMaxSize()
                .clip(RoundedCornerShape(roundedCorners.value))
        ) {
            homeContent()
        }
    }
}