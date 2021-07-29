package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.androiddevchallenge.data.models.Photo
import com.example.androiddevchallenge.ui.MainViewModel
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.components.PhotoItem

@Composable
fun PhotoList(viewModel: MainViewModel, navController: NavController) {
    val photos = viewModel
        .photosState
        .collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(photos.value) { index, photo ->
            PhotoItem(
                photo = photo,
                onClick = {
                    viewModel.setSelectedIndex(index)
                    navController.navigate(Screen.DetailsScreen.route)
                }
            )
        }
    }
}
