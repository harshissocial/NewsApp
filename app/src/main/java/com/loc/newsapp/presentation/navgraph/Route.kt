package com.loc.newsapp.presentation.navgraph

sealed class Route(
    val route: String
) {
    object OnboardingScreen: Route(route = "onboardingScreen")
    object HomeScreen: Route(route = "homescreen")
    object SearchScreen: Route(route = "searchScreen")
    object BookmarkScreen: Route(route = "bookmarkScreen")
    object DetailScreen: Route(route = "detailScreen")

    object AppStartNavigation: Route("appStartNavigation")
    object NewsNavigation: Route("newsNavigation")
    object NewsNavigatorScreen: Route("newsNavigator")
}