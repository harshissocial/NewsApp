package com.loc.newsapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.newsapp.domain.usescases.news.NewsUsesCases
import com.loc.newsapp.domain.usescases.news.SearchNews
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUsesCases: NewsUsesCases
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(
                    searchQuery = event.searchQuery
                )
            }

            is SearchEvent.SearchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles = newsUsesCases.searchNews(
            searchQuery = state.value.searchQuery,
            sources = listOf("bbb-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(
            articles = articles
        )
    }

}