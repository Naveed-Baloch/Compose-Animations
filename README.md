# Compose Animation 
This repository covers various animations using Jetpack Compose, making animations smooth and easy to build. Explore practical examples for an engaging learning experience.

## 1. Items Placement  
We can easly animate the offset of the items of LazyRow/Column or Grid by just adding the `animateItemPlacement` to modifier of the Item. 
#### Steps 
1. Add the key attribute to the `items` in LazyRow/Column or Grid. <br>
   `items(items = list.value, key = { it.id })`
2. Create Animation <br>
   `val itemPlacementAnim: FiniteAnimationSpec<IntOffset> = tween(700, easing = LinearOutSlowInEasing)`
3. Add the `animateItemPlacement` to Item Modifier <br>
`PlayerItem(player = it, modifier = Modifier.animateItemPlacement(itemPlacementAnim))`
4. Thats it now the whenever the Items placement will be changed then Animation will make the magic 🪄 <br>
5. Checkout the [Code File](https://github.com/Naveed-Baloch/Compose-Animations/blob/cd46ff7599571a1cda144fd2bf0af6b9b277dc94/app/src/main/java/com/naveed/composeanimations/itemplacement/ItemPlacementComponents.kt) here. 
#### Demo 
https://github.com/Naveed-Baloch/Compose-Animations/assets/83871075/232f6734-5e85-4a10-bc47-9e25972dfadd


