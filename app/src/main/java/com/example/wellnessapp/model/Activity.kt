package com.example.wellnessapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

//This is a data class that defines the information that will be provided for each activity card
data class Activity(
    val day: Int,
    @StringRes val title: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val description: Int
)
