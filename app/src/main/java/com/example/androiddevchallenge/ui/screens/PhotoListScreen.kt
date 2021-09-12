package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.ui.MainViewModel
import com.example.androiddevchallenge.ui.components.PhotoItem

@Composable
fun PhotoList(viewModel: MainViewModel, forwardNavigation: () -> Unit) {
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
                    forwardNavigation()
                }
            )
        }
    }
}
