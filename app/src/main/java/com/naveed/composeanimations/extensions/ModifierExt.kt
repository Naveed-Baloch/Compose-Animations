package com.naveed.composeanimations.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.clickableWithoutRipple(enabled: Boolean = true, onClick: () -> Unit): Modifier = composed {
    this then Modifier.clickable(enabled = enabled, indication = null, interactionSource = remember { MutableInteractionSource() }, onClick = onClick)
}
