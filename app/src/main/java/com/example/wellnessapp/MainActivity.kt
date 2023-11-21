package com.example.wellnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
                    modifier = Modifier.fillMaxSize()
                ) {
                    App()
                }
            }
        }
    }
}
@Composable
fun App(){
    var changeScreens by remember { mutableStateOf(false) }
    HomeScreen(onClick = { changeScreens = true } )
    if(changeScreens) {
        WellnessApp()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onClick: () -> Unit) {
    Scaffold(
        topBar = {
            WellnessAppTopAppBar()
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
                .fillMaxWidth()
        ) {
        Text(text = "hey welcome")
        Button(onClick = onClick) {
            Text("click here")
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
                .background(MaterialTheme.colorScheme.primary)
        ) {
            when (week) {
                1 -> {
                    LazyColumn(
                        modifier = Modifier.padding(18.dp)
                    ) {
                        item { WelcomeMessage(modifier = modifier) }
                        item {
                            Text(
                                text = "Week $week",
                                color = Color.Black,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = modifier.padding(bottom = 10.dp))
                        }
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
                        week = week,
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
        modifier = modifier,
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.logo1),
                    contentDescription = null,
                    modifier = modifier.size(44.dp)
                )
                Spacer(modifier = modifier.size(16.dp))
                Image(
                    painter = painterResource(R.drawable.logo2),
                    contentDescription = null,
                    modifier = modifier.size(168.dp)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun WelcomeMessage(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.welcome),
        color = Color.Black,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .padding(bottom = 18.dp)
    )
}

@Composable
fun WellnessActivity(
    activity: Activity,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val currentWeek by rememberUpdatedState(activity.week)

    LaunchedEffect(currentWeek) {
        expanded = false
    }
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .padding(bottom = 16.dp)
    ) {
        Column(modifier = modifier.background(if (!expanded) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onTertiary)) {
            Row(
                modifier = modifier
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
    week: Int,
    activities: List<Activity>,
    onClickBack: () -> Unit,
    onClickForward: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn (modifier = Modifier.padding(18.dp)) {
        item {
            Text(
                text="Week $week",
                style = MaterialTheme.typography.bodyLarge
            )
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
            text = "Day $day",
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(title),
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun ActivityDescription(description: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(description),
        style = MaterialTheme.typography.bodySmall,
        color = Color.Black,
        modifier = modifier.padding(12.dp)
    )
}

@Composable
fun ActivityButton(expanded: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            tint = Color.Black,
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
            .size(78.dp)
            .padding(12.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
fun PageButtons(
    onClickBack: () -> Unit,
    onClickForward: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row {
        ElevatedButton(
            onClick = onClickBack,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)

        ) {
            Text(
                "Previous",
                color = Color.Black
            )
        }
        Spacer(modifier = modifier.weight(1f))
        ElevatedButton(
            onClick = onClickForward,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                "Next",
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessAppPreview() {
    WellnessAppTheme {
        App()
    }
}