package com.example.wellnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wellnessapp.data.Activity
import com.example.wellnessapp.data.activities
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
    Scaffold(
        topBar = {
            WellnessAppTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(activities) {
                WellnessActivity(
                    activity = it,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

}

@Composable
fun WellnessActivity(
    activity: Activity,
    modifier: Modifier = Modifier
){
    Card {
        Column {
            ActivityInfo(activity.day, activity.title)
            ActivityImage(activity.imageResourceId)
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
fun ActivityImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier = modifier

    )
}

@Preview(showBackground = true)
@Composable
fun WellnessAppPreview() {
    WellnessAppTheme {
        WellnessApp()
    }
}


