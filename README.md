# Compose Animation 
This repository covers various animations using Jetpack Compose, making animations smooth and easy to build. Explore practical examples for an engaging learning experience.

## 1. Items Placement  
| Description | Demo |
| ------------- | ------------- |
| <b>We can easly animate the offset of the items of LazyRow/Column or Grid by just adding the `animateItemPlacement` to modifier of the Item.<b> <h3>Steps</h3> 1. Add the key attribute to the `items` in LazyRow/Column or Grid. <br>`items(items = list.value, key = { it.id })`<br>2. Create Animation <br>`val itemPlacementAnim: FiniteAnimationSpec<IntOffset> = tween(700, easing = LinearOutSlowInEasing)`<br>3. Add the `animateItemPlacement` to Item Modifier <br>`PlayerItem(player = it, modifier = Modifier.animateItemPlacement(itemPlacementAnim))`<br>4. Thats it now the whenever the Items placement will be changed then Animation will make the magic 🪄 <br>5. Checkout the [Code File](https://github.com/Naveed-Baloch/Compose-Animations/blob/cd46ff7599571a1cda144fd2bf0af6b9b277dc94/app/src/main/java/com/naveed/composeanimations/itemplacement/ItemPlacementComponents.kt) here. | <img align="right" alt="GIF" src="https://github.com/Naveed-Baloch/Compose-Animations/assets/83871075/b18ed99b-a36e-4c7e-81aa-f634d69fa5f3" width="300"/> |
|  |    |

## 2. Animated Drawer
| Description | Demo |
| ------------- | ------------- |
| <b>You can now utilize the AnimatedDrawer, which is totally customizable with the following features:</b><br><br>1️⃣ Control Rotation.<br>2️⃣ Change Background Colors, which are optional. <br>3️⃣ Change Offset Animation Drawers. <br>4️⃣ Has Content slots for Drawer & Selected Page Content. <br><br>Checkout the [Code File](https://github.com/Naveed-Baloch/Compose-Animations/blob/main/app/src/main/java/com/naveed/composeanimations/animateddrawer/AnimatedDrawer.kt) here.<br><br><br><br><br><br><br><br>| <img align="right" alt="GIF" src="https://github.com/Naveed-Baloch/Compose-Animations/assets/83871075/1ff9f5ca-027e-47ac-b23f-807f33e4334f" width="300"/> |
|  |    |

## 3. Animated Pie Chart 
| Description   | Demo |
| ------------- | ------------- |
| <b>With Animated Pie Chart you can show the Stats of any data which has following features</b><br><br>1️⃣ Control Spacing B/W Pie Chart Slices.<br>2️⃣ Change Radius of Pie chart & Slice Width. <br>3️⃣ Show Slices in Asecending, descending order or default as per the submitted data order<br>4️⃣ Control the Animation of Slices.<br><br>Checkout the [Code File](https://github.com/Naveed-Baloch/Compose-Animations/blob/main/app/src/main/java/com/naveed/composeanimations/animatedPieChart/AnimatedPieChart.kt) here.  | <img align="right" alt="GIF" src="https://github.com/Naveed-Baloch/Compose-Animations/assets/83871075/435d7d06-fb76-44c0-afa2-47df362b5591" width="300"/> |
|  |    |
