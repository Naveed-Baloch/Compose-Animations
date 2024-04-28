package com.naveed.composeanimations.itemplacement

import android.content.res.Resources.Theme
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naveed.composeanimations.R
import com.naveed.composeanimations.itemplacement.ItemPlacementComponents.ItemsPlacementAnim
import com.naveed.composeanimations.ui.theme.ComposeAnimationsTheme


object ItemPlacementComponents {

    data class Player(
        val id: Int,
        val name: String,
        @DrawableRes val imageId: Int
    )

    private val playersList by lazy {
        listOf(
            Player(id = 1, name = "Mbappe", imageId = R.drawable.avatar_1),
            Player(id = 2, name = "Harry", imageId = R.drawable.avatar_2),
            Player(id = 3, name = "Erling", imageId = R.drawable.avatar_3),
            Player(id = 5, name = "Jude", imageId = R.drawable.avatar_4),
            Player(id = 6, name = "Kevin", imageId = R.drawable.avatar_3),
            Player(id = 7, name = "Messi", imageId = R.drawable.avatar_2)
        )
    }

    @Composable
    fun PlayerItem(player: Player, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier.requiredHeight(120.dp).requiredWidth(100.dp)
        ) {
            Image(
                modifier = Modifier
                    .requiredHeight(120.dp).requiredWidth(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(width = 5.dp, shape = RoundedCornerShape(10.dp), color = Color.White),
                painter = painterResource(id = player.imageId),
                contentScale = ContentScale.Crop ,
                contentDescription = "Player Image"
            )

            Text(
                text = player.name,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .fillMaxWidth()
                    .requiredHeight(20.dp)
                    .background(Color.White),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ItemsPlacementAnim() {
        val list = remember { mutableStateOf(playersList) }
        val itemPlacementAnim: FiniteAnimationSpec<IntOffset> = tween(700, easing = LinearOutSlowInEasing)
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color.Black)
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.spacedBy(100.dp, Alignment.CenterVertically)
            ) {
                items(
                    items = list.value,
                    key = { it.id }
                ) {
                    PlayerItem(player = it, modifier = Modifier.animateItemPlacement(itemPlacementAnim))
                }
            }
            Text(
                text = "VS",
                fontSize = 35.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )

            Button(
                onClick = { list.value = list.value.shuffled() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(30.dp)
            ) {
                Text(text = "Shuffle Teams")
            }
        }
    }
}


@Preview
@Composable
fun PlayerPreview() {
    val player = ItemPlacementComponents.Player(
        name = "M-Salah", imageId = R.drawable.avatar_3, id = 1
    )
    ComposeAnimationsTheme {
        ItemPlacementComponents.PlayerItem(player)
    }
}

@Preview
@Composable
fun TeamsPreview() {
    ComposeAnimationsTheme {
        ItemsPlacementAnim()
    }
}