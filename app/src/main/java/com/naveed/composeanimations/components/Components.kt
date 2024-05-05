package com.naveed.composeanimations.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.naveed.composeanimations.extensions.clickableWithoutRipple

object Components {
    @Composable
    fun ComingSoon() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(text = "Coming Soon!", modifier = Modifier.align(Alignment.Center))
        }
    }

    @Composable
    fun ToolBar(title: String, onToggleDrawer: () -> Unit) {
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Menu, contentDescription = "", tint = Color.White,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clickableWithoutRipple {
                        onToggleDrawer()
                    }
            )
            Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }

}