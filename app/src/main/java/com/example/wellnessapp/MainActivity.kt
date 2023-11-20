package com.example.wellnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wellnessapp.data.Activity
import com.example.wellnessapp.data.activities1
import com.example.wellnessapp.data.activities2
import com.example.wellnessapp.data.activities3
import com.example.wellnessapp.data.activities4
import com.example.wellnessapp.data.activities5
import com.example.wellnessapp.ui.theme.WellnessAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WellnessAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WellnessApp()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessApp(modifier: Modifier = Modifier) {
    var week by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            WellnessAppTopAppBar()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (week) {
                1 -> {
                    LazyColumn(
                        modifier = Modifier // Adjust the padding as needed
                    ) {
                        item { WelcomeMessage(modifier = modifier) }
                        item { WeekNumber(weekNumber = week) }
                        items(activities1) { activity ->
                            WellnessActivity(
                                activity = activity
                            )
                        }
                        item {
                            PageButtons(
                                onClickBack = { week = 1 },
                                onClickForward = { week++ }
                            )
                        }
                    }
                }
                2, 3, 4, 5 -> {
                    ActivityList(
                        weekNumber = week,
                        activities = activityByWeek(week = week),
                        onClickBack = { week-- },
                        onClickForward = { if (week == 5) week = 5 else week++ })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessAppTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(R.drawable.top_app_bar),
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .size(750.dp)
            )
        },
    )
}

@Composable
fun WelcomeMessage(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.welcome),
        modifier = modifier
            .padding(5.dp)
    )
}

@Composable
fun WellnessActivity(
    activity: Activity,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .padding(bottom = 16.dp)

    ) {
        Column {
            Row(
                modifier = modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
            ) {
                ActivityImage(activity.imageResourceId)
                ActivityInfo(activity.day, activity.title)
                Spacer(modifier = modifier.weight(1f))
                ActivityButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                    modifier = modifier
                )
            }
            if (expanded) {
                ActivityDescription(activity.description)
            }
        }
    }
}

@Composable
fun ActivityList(
    weekNumber: Int,
    activities: List<Activity>,
    onClickBack: () -> Unit,
    onClickForward: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        item {
            WeekNumber(weekNumber = weekNumber)
        }
        items(activities) { activity ->
            WellnessActivity(
                activity = activity
            )
        }
        item {
            PageButtons(
                onClickBack = onClickBack,
                onClickForward = onClickForward,
                modifier = modifier
            )
        }
    }
}

@Composable
fun activityByWeek(week: Int): List<Activity> {
    return when (week) {
        2 -> activities2
        3 -> activities3
        4 -> activities4
        5 -> activities5
        else -> {
            activities1
        }
    }
}

@Composable
fun ActivityInfo(
    day: Int,
    title: Int,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "Day $day"
        )
        Text(
            text = stringResource(title)
        )
    }
}

@Composable
fun ActivityDescription(description: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(description)
    )
}

@Composable
fun WeekNumber(weekNumber: Int, modifier: Modifier = Modifier) {
    Text(text = "Week $weekNumber")
}

@Composable
private fun ActivityButton(expanded: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = null
        )
    }
}

@Composable
fun ActivityImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(64.dp)
    )
}

@Composable
fun PageButtons(
    onClickBack: () -> Unit,
    onClickForward: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row {
        ElevatedButton(onClick = onClickBack) {
            Text("Previous")
        }
        Spacer(modifier = modifier.weight(1f))
        ElevatedButton(onClick = onClickForward) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessAppPreview() {
    WellnessAppTheme {
        WellnessApp()
    }
}