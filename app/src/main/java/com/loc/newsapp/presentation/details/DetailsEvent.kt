package com.loc.newsapp.presentation.details

sealed class DetailsEvent {
    object SaveArticleEvent: DetailsEvent()
}