package com.loc.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.Dimens.MediumPadding1
import com.loc.newsapp.presentation.Dimens.MediumPadding2
import com.loc.newsapp.presentation.Dimens.SmallPadding

@Composable
fun ArticleList(
    articles: List<Article>,
    onClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    if(articles.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        //contentPadding = PaddingValues(all = SmallPadding)
    ) {
        items(count = articles.size) {
            val article = articles[it]
            ArticleCard(article = article, onClick = { onClick(article) })
        }
    }
}

@Composable
fun ArticleList(
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding2),
            //contentPadding = PaddingValues(all = SmallPadding)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onClick = { onClick(article) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error)
            false
        }

        articles.itemCount == 0 -> {
            EmptyScreen()
            return false
        }

        else -> true
    }
}

@Composable
private fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(MediumPadding1)
    ) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}