package com.example.androiddevchallenge.ui

sealed class Screen(val route: String) {
    object ListScreen : Screen("list")
    object DetailsScreen : Screen("details")
}