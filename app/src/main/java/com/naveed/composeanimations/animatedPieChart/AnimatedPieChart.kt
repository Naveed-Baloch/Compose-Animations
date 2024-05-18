package com.naveed.composeanimations.animatedPieChart


import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naveed.composeanimations.animatedPieChart.PieChartDataOrder.Ascending
import com.naveed.composeanimations.animatedPieChart.PieChartDataOrder.Descending
import com.naveed.composeanimations.animatedPieChart.PieChartDataOrder.None
import kotlinx.coroutines.delay

data class SliceMetaData(
    val startAngle: Float,
    val sweepAngle: Float,
    val color: Color,
)

private fun buildPieChartSlices(sliceData: List<SliceData>, itemsSpacing: Int): List<SliceMetaData> {
    var lastValue = 0f
    val totalSum = sliceData.sumOf { it.value }
    val pieChartSlices = mutableListOf<SliceMetaData>()
    sliceData.forEach { value ->
        val sweepAngle = (360 - sliceData.size * itemsSpacing) * value.value / totalSum.toFloat()
        val slice = SliceMetaData(
            startAngle = lastValue,
            sweepAngle = sweepAngle,
            color = value.color,
        )
        pieChartSlices.add(slice)
        lastValue += (sweepAngle + itemsSpacing)
    }
    return pieChartSlices.toList()
}

@Composable
fun PieChart(
    sliceData: List<SliceData>,
    dataDisplayOrder: PieChartDataOrder,
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 10.0) itemsSpacing: Float = 5f,
    radius: Dp = 50.dp,
    pieChartSliceWidth: Dp = 30.dp,
) {
    val sortedSlices = remember(itemsSpacing, dataDisplayOrder) {
        when (dataDisplayOrder) {
            Ascending -> sliceData.sortedBy { it.value }
            Descending -> sliceData.sortedByDescending { it.value }
            None -> sliceData
        }
    }
    val chartSlices = remember(itemsSpacing, dataDisplayOrder) { buildPieChartSlices(sortedSlices, itemsSpacing.toInt()) }

    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.spacedBy(30.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Pie Chart
            Box {
                chartSlices.forEachIndexed { index, slice ->
                    var animate by remember { mutableStateOf(false) }
                    val animatedSweepAngle by animateFloatAsState(targetValue = if (animate) slice.sweepAngle else 0f, label = "", animationSpec = tween(600))
                    LaunchedEffect(key1 = Unit) {
                        delay(300L * index)
                        animate = true
                    }
                    Canvas(
                        modifier = Modifier
                            .size(radius * 2f)
                            .rotate(-90f)
                    ) {
                        drawArc(
                            color = slice.color,
                            startAngle = slice.startAngle,
                            sweepAngle = animatedSweepAngle,
                            useCenter = false,
                            style = Stroke(pieChartSliceWidth.toPx(), cap = StrokeCap.Butt)
                        )
                    }
                }
            }
            // Meta Data
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier,
            ) {
                items(sortedSlices) {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.Start)
                    ) {
                        Box(modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(it.color))
                        Text(text = it.name + ": " + it.value, fontSize = 16.sp, modifier = Modifier, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

enum class PieChartDataOrder { Ascending, Descending, None }
data class SliceData(
    val name: String,
    val value: Int,
    val color: Color,
)

private fun data(): List<SliceData> {
    val flutter = SliceData(name = "Flutter", value = 150, color = Color.Blue)
    val android = SliceData(name = "Android Native", value = 300, color = Color.Green)
    val firebase = SliceData(name = "Firebase", value = 100, color = Color.Red)
    val kmp = SliceData(name = "KMP", value = 50, color = Color.Black)
    return listOf(flutter, android, firebase, kmp)
}


@Composable
fun PieChartScreen() {
    val pieChartData = remember { data() }
    var dataDisplayOrder by remember { mutableStateOf(None) }
    var hasItemSpacing by remember { mutableStateOf(false) }
    var showChart by remember { mutableStateOf(false) }
    val animatedItemSpacing by animateFloatAsState(targetValue = if (hasItemSpacing) 5f else 0f, label = "")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (showChart) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                PieChart(
                    itemsSpacing = animatedItemSpacing,
                    sliceData = pieChartData,
                    dataDisplayOrder = dataDisplayOrder,
                    radius = 100.dp
                )
            }
        }

        // Debug Actions
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .align(Alignment.BottomCenter)
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterHorizontally)
            ) {
                TextCheckBox(title = "Populate Data", checked = showChart, onClick = { showChart = !showChart } )
                TextCheckBox(title = "Spacing", checked = hasItemSpacing, onClick =  { hasItemSpacing = !hasItemSpacing })
            }
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterHorizontally)
            ) {
                TextCheckBox(title = "Ascending", checked = dataDisplayOrder == Ascending, onClick = { dataDisplayOrder = Ascending })
                TextCheckBox(title = "Descending", checked = dataDisplayOrder == Descending, onClick =  { dataDisplayOrder = Descending })
                TextCheckBox(title = "None", checked = dataDisplayOrder == None, onClick =  { dataDisplayOrder = None })
            }
        }
    }
}

@Composable
private fun TextCheckBox(title: String, checked: Boolean, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, color = MaterialTheme.colorScheme.onPrimaryContainer)
        Checkbox(
            checked = checked,
            onCheckedChange = { onClick() },
        )
    }
}

@Composable
@Preview
private fun PieChartPreview() {
    PieChartScreen()
}