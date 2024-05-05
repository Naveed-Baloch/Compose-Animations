package com.naveed.composeanimations

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.naveed.composeanimations.animateddrawer.AnimatedDrawer
import com.naveed.composeanimations.components.Components.ComingSoon
import com.naveed.composeanimations.components.Components.ToolBar
import com.naveed.composeanimations.extensions.clickableWithoutRipple
import com.naveed.composeanimations.itemplacement.ItemPlacementComponents

enum class Page {
    ItemPlacement, AnimatedDialog, Logout
}

val pageList by lazy {
    listOf(Page.ItemPlacement, Page.AnimatedDialog, Page.Logout)
}


fun Page.toText() = when (this) {
    Page.ItemPlacement -> "Item Placements"
    Page.AnimatedDialog -> " Animated Dialog"
    Page.Logout -> "Authentication"
}

enum class DrawerState {
    OPENED, CLOSED
}

@Composable
fun Page.UI() = when (this) {
    Page.ItemPlacement -> ItemPlacementComponents.ItemsPlacementAnim()
    Page.Logout -> ComingSoon()
    Page.AnimatedDialog -> ComingSoon()
}

@Composable
fun DrawerListItem(page: Page, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .padding(start = 0.dp)
            .height(50.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent)
            .clickableWithoutRipple { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
    ) {
        val contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        Icon(
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = "",
            tint = contentColor,
        )
        Text(text = page.toText(), color = contentColor, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun DrawerContent(selectedPage: Page, onClick: (Page) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
                .padding(start = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar_3),
                contentDescription = "LoggedIn User Image",
                modifier = Modifier
                    .size(75.dp)
                    .border(width = 3.dp, color = MaterialTheme.colorScheme.onPrimaryContainer, shape = CircleShape)
                    .clip(CircleShape)
            )

            pageList.forEach {
                DrawerListItem(page = it, isSelected = selectedPage == it, onClick = { onClick(it) })
            }
        }
    }
}


@Composable
fun MainScreen() {
    var drawerState by remember { mutableStateOf(DrawerState.CLOSED) }
    var selectedPage by remember { mutableStateOf(Page.ItemPlacement) }

    AnimatedDrawer(
        backgroundColors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
        ),
        drawerState = drawerState,
        homeContent = {
            selectedPage.UI()
            ToolBar(title = selectedPage.toText()) {
                drawerState = if (drawerState == DrawerState.OPENED) DrawerState.CLOSED else DrawerState.OPENED
            }
        },
        drawerContent = {
            DrawerContent(selectedPage = selectedPage) { page ->
                drawerState = DrawerState.CLOSED
                selectedPage = page
            }
        }
    )
}