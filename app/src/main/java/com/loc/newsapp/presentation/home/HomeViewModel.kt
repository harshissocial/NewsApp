package com.loc.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.newsapp.domain.usescases.news.NewsUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    newsUsesCases: NewsUsesCases
) : ViewModel() {

    val news = newsUsesCases.getNews(
        sources = listOf("bbb-news", "abc-news", "al-jazeera-english")
    ).cachedIn(viewModelScope)

}