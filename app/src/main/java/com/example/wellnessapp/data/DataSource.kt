package com.example.wellnessapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.wellnessapp.R

//This class returns a list of the activities & each activity's information

data class Activity(
    val day: Int,
    @StringRes val title: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val description: Int
)

    val activities =  listOf<Activity> (
            Activity(1, R.string.meditate, R.drawable.meditate, R.string.meditate_des),
            Activity(2, R.string.yoga, R.drawable.yoga, R.string.yoga_des),
            Activity(3, R.string.stretch, R.drawable.stretch, R.string.stretch_des),
            Activity(4, R.string.cooking, R.drawable.cook, R.string.cooking_des),
            Activity(5, R.string.read_book, R.drawable.read_book, R.string.read_book_des),
            Activity(6, R.string.cardio, R.drawable.cardio, R.string.cardio_des),
            Activity(7, R.string.walk, R.drawable.walking, R.string.walk_des),
            Activity(8, R.string.lift, R.drawable.lift, R.string.lift_des),
            Activity(9, R.string.music, R.drawable.music, R.string.music_des),
            Activity(10, R.string.podcast, R.drawable.podcast, R.string.podcast_des),
            Activity(11, R.string.clean, R.drawable.clean, R.string.clean_des),
            Activity(12, R.string.call_friend, R.drawable.call_friend, R.string.call_friend_des),
            Activity(13, R.string.journal, R.drawable.journal, R.string.journal_des),
            Activity(14, R.string.nap, R.drawable.nap, R.string.nap_des),
            Activity(15, R.string.drink_tea, R.drawable.drink_tea, R.string.drink_tea_des),
            Activity(16, R.string.dance, R.drawable.dance, R.string.dance_des),
            Activity(17, R.string.drink_water, R.drawable.drink_water, R.string.drink_water_des),
            Activity(18, R.string.facial, R.drawable.facial_massage, R.string.facial_des),
            Activity(19, R.string.hike, R.drawable.hike, R.string.hike_des),
            Activity(20, R.string.read_article, R.drawable.read_article, R.string.read_article_des),
            Activity(21, R.string.watch_tv, R.drawable.watch_tv, R.string.watch_tv_des),
            Activity(22, R.string.gratitude_list, R.drawable.gratitude_list, R.string.gratitude_list_des),
            Activity(23, R.string.learn_language, R.drawable.learn_language, R.string.learn_language_des),
            Activity(24, R.string.fruits_veggies, R.drawable.fruits_veggies, R.string.fruits_veggies_des),
            Activity(25, R.string.counseling, R.drawable.counseling, R.string.counseling_des),
            Activity(26, R.string.thank_someone, R.drawable.thank_someone, R.string.thank_someone_des),
            Activity(27, R.string.puzzle, R.drawable.puzzle, R.string.puzzle_des),
            Activity(28, R.string.take_class, R.drawable.take_class, R.string.take_class_des),
            Activity(29, R.string.play_game, R.drawable.play_game, R.string.play_game_des),
            Activity(30, R.string.volunteer, R.drawable.volunteer, R.string.volunteer_des),
            Activity(31, R.string.flowers, R.drawable.flowers, R.string.flowers_des),
        )

