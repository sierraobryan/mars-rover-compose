package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.models.Photo
import com.example.androiddevchallenge.ui.components.PhotoItem

@Composable
fun PhotosDetails(photo: Photo, isShown: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            IconButton(
                onClick = isShown,
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Close photo details",
                    tint = MaterialTheme.colors.onSurface
                )
            }
            PhotoItem(photo = photo)
            Text(text = "Landing date: " + photo.rover.landingDate)
            Text(text = "Camera: " + photo.camera.fullName)
            Text(text = "Rover:" + photo.rover.name)
        }
    }
}