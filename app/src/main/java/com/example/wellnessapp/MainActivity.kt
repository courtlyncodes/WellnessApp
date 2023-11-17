package com.example.wellnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun WellnessApp(modifier: Modifier = Modifier) {

}

@Composable
fun ActivityCard(
    day: String,
    title: String,
    image: Painter,
    modifier: Modifier = Modifier
) {
    Card() {
        Text(
            text = day
        )
        Text(
            text = title
        )
        Image(
            painter = image,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessAppPreview() {
    WellnessAppTheme {

    }
}