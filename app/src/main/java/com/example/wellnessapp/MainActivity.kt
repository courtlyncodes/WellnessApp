@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.wellnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
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
                    WellnessApp(name = "Court")
                }
            }
        }
    }
}

//Shows the home screen or wellness app based on user submit
@Composable
fun App() {
    var text by remember { mutableStateOf("") } //Stores the user input for 'name'
    var changeScreens by remember { mutableStateOf(false) } //Determines if screens should be changed based on button click
    HomeScreen(
        name = text,
        onClick = {
            changeScreens = true
        }, //If user clicks "submit" or presses enter, state is changed to true
        onTextChange = { text = it }
    )
    if (changeScreens) { //If state is true (user clicked "submit" or pressed enter), the wellness app (activities) are shown
        WellnessApp(name = text)
    }
}

//Renders the home screen when a user opens the app
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalTextApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun HomeScreen(
    name: String,
    onClick: () -> Unit,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val rainbowColors = listOf( //List of colors for user input
        Color(0xFF9575CD),
        Color(0xFFBA68C8),
        Color(0xFFE57373),
        Color(0xFFFFB74D),
        Color(0xFFFFF176),
        Color(0xFFAED581),
        Color(0xFF4DD0E1),
        Color(0xFF9575CD)
    )
    val brush = remember { //Brush to create gradient of colors for user input
        Brush.linearGradient(
            colors = rainbowColors
        )
    }
    val keyboardController =
        LocalSoftwareKeyboardController.current //Controls visibility of the keyboard
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Image(
            painter = painterResource(R.drawable.home_screen_logo),
            contentDescription = "green flower and NooGlow logo",
            modifier = modifier.size(200.dp)
        )
        WelcomeMessages()
        OutlinedTextField(value = name, //Takes user input for their name
            onValueChange = { onTextChange(it) },
            label = {
                Text(
                    "Enter Your Name",
                    color = Color.Gray,
                    textAlign = TextAlign.Start
                )
            },
            textStyle = TextStyle(brush = brush),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done), //Signals a checkmark on the keyboard to represent that the user is done with input
            keyboardActions = KeyboardActions(onDone = { //When the done button is clicked the user's input is submitted & they are taken to the app
                onClick()
                keyboardController?.hide() //Hides the keyboard upon screen change
            }),
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .onKeyEvent { event -> //When user presses enter on the keyboard they are taken to the app
                    if (event.key == Key.Enter) {
                        onClick()
                        keyboardController?.hide()
                        true
                    } else {
                        false
                    }
                })
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer),
            modifier = modifier
                .align(Alignment.End)
                .padding(end = 25.dp)
        ) {
            Text(
                "Submit",
                color = Color.Black
            )
        }
    }
}

//Function to store the welcome messages on the home screen
@Composable
fun WelcomeMessages(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(start = 25.dp, end = 25.dp, bottom = 25.dp)) {
        Text(
            text = stringResource(R.string.welcome1),
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = modifier.height(10.dp))
        Text(
            text = stringResource(R.string.welcome2),
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

//Renders the Wellness App - shows each activity by week along with a message on the first page
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessApp(name: String, modifier: Modifier = Modifier) {
    var week by remember { mutableStateOf(1) } //State to render the week based on page button click
    Scaffold(topBar = {
        WellnessAppTopAppBar() //Top App Bar
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            when (week) { //When conditional to render page based on the week
                1 -> { //Week 1 has its own lazy column to included the greeting and how-to message along with the week 1 activities
                    LazyColumn(
                        modifier = Modifier.padding(18.dp)
                    ) {
                        item {//Renders a greeting based on the user's inputted name
                            Text(
                                text = "Hello $name!",
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }
                        item {//Renders a 'how-to' message on the first page
                            Text(
                                text = stringResource(R.string.how_to1),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = modifier.padding(start = 25.dp, top = 25.dp, end = 25.dp)
                            )
                        }
                        item {//Renders a 'how-to' message on the first page
                            Text(
                                text = stringResource(R.string.how_to2),
                                color = Color.Black,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = modifier.padding(
                                    start = 60.dp,
                                    top = 10.dp,
                                    end = 35.dp,
                                    bottom = 25.dp
                                )
                            )
                        }
                        item {//Shows the week number for week 1 above the activity list
                            Text(
                                text = "Week $week",
                                color = Color.Black,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = modifier.padding(bottom = 10.dp)
                            )
                        }
                        items(activities1) { activity -> //Renders the activity cards for week 1
                            WellnessActivity(
                                activity = activity
                            )
                        }
                        item { //Renders page buttons at bottom of screen
                            PageButtons(
                                onClickBack = { week = 1 }, //Page does not change if week is week 1
                                onClickForward = { week++ })
                        }
                    }
                }

                2, 3, 4, 5 -> { //Second part of conditional that shows activity cards based on the week number
                    ActivityList(week = week,
                        activities = activityByWeek(week = week),
                        onClickBack = { week-- }, //Week is decreased and changes to previous week when "previous" button is clicked
                        onClickForward = {
                            if (week == 5) week =
                                5 else week++ //Page does not change if the week is week 5, otherwise the week is increased and changes to the next week
                        })
                }
            }
        }
    }
}

//Creates each activity card with the activity's image, basic information, & expandable button to show an optional description
@Composable
fun WellnessActivity(
    activity: Activity,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) } //State that determines if expanded button is clicked
    val currentWeek by rememberUpdatedState(activity.week) //State that determines the week based on "Next" or "Previous" button click
    var completed by remember { mutableStateOf(false) }
    LaunchedEffect(currentWeek) {  //Determines if the week has been changed. If so, all cards are reset to normal state (checkboxes and descriptions disappear)
        expanded = false
        completed = false
    }
    if (!completed) { //If checkbox has not been checked
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = modifier.padding(bottom = 16.dp)
        ) {
            Column(
                modifier = modifier
                    .background(if (!expanded) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onTertiary)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
            ) { //Changes the color of the card if the card is expanded

                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    ActivityImage(activity.imageResourceId)
                    ActivityInfo(activity.day, activity.title)
                    Spacer(modifier = modifier.weight(1f))
                    ActivityButton(
                        expanded = expanded,
                        onClick = { expanded = !expanded },
                        modifier = modifier
                    )
                    Checkbox(
                        checked = completed,
                        onCheckedChange = { completed = !completed; }
                    )
                }
                if (expanded) { //If the expanded button is clicked the description of the activity is shown underneath the activity's information
                    ActivityDescription(activity.description)
                }
            }
        }
    } else { //Collapses card when checkbox is clicked
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = modifier.padding(bottom = 16.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = "Day ${activity.day}: " + stringResource(activity.title),
                    color = Color.Black,
                    modifier = modifier.padding(start = 10.dp, top = 10.dp)
                )
                Spacer(modifier = modifier.weight(1f))
                Checkbox(
                    checked = true,
                    onCheckedChange = { completed = !completed },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        checkmarkColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            }
        }
    }
}

//Renders a list of activity cards
@Composable
fun ActivityList(
    week: Int,
    activities: List<Activity>,
    onClickBack: () -> Unit,
    onClickForward: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = Modifier.padding(18.dp)) {
        item { //Shows the week
            Text(
                text = "Week $week",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        items(activities) { activity -> //Shows the list of activity cards
            WellnessActivity(
                activity = activity
            )
        }
        item { //Shows the page buttons at the bottom of each page
            PageButtons(
                onClickBack = onClickBack,
                onClickForward = onClickForward,
                modifier = modifier
            )
        }
    }
}


//Top App Bar function
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
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary) //Sets the background color of the top app bar
    )
}

//Shows the basic information for each activity, including day and the title
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

//Optional description of each activity
@Composable
fun ActivityDescription(
    description: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(description),
        style = MaterialTheme.typography.bodySmall,
        color = Color.Black,
        modifier = modifier.padding(12.dp)
    )
}

//Renders an image for each activity
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

//Expandable button to render each activity's description
@Composable
fun ActivityButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, //changes the direction of the button when clicked to expand or condense
            tint = Color.Black,
            contentDescription = null
        )
    }

}

//Previous and Next buttons
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
                "Previous", color = Color.Black
            )
        }
        Spacer(modifier = modifier.weight(1f))
        ElevatedButton(
            onClick = onClickForward,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                "Next", color = Color.Black
            )
        }
    }
}

//Return list of activities for weeks 2 - 5
@Composable
private fun activityByWeek(week: Int): List<Activity> {
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

@Preview(showBackground = true)
@Composable
fun WellnessAppPreview() {
    WellnessAppTheme {
        WellnessApp(name = "Court")
    }
}