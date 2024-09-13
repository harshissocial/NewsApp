package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Welcome to the News App",
        description = "Discover the latest news and stay informed.",
        image = R.drawable.o1,
    ),
    Page(
        title = "Search News",
        description = "Find news from sources of your interests",
        image = R.drawable.o2
    ),
    Page(
        title = "Bookmark Your Favorites",
        description = "Save articles for later reading on your device",
        image = R.drawable.o3,
    )
)