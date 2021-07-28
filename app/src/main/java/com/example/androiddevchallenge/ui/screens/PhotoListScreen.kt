package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.data.models.Photo
import com.example.androiddevchallenge.ui.components.PhotoItem

@Composable
fun PhotoList(photos: List<Photo>, onClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(photos) { index, photo ->
            PhotoItem(
                photo = photo,
                onClick = {
                    onClick(index)
                }
            )
        }
    }
}
