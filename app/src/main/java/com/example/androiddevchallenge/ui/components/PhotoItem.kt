package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.models.Photo
import com.example.androiddevchallenge.ui.theme.teal200
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun PhotoItem(photo: Photo, onClick: (() -> Unit)? = null) {
    Image(
        painter = rememberCoilPainter(makeSecure(photo.imgSrc)),
        contentDescription = "Mars Photo on ${photo.earthDate} from ${photo.rover.name}",
        modifier = Modifier
            .padding(8.dp)
            .clip(CircleShape)
            .background(color = teal200)
            .padding(12.dp)
            .clip(CircleShape)
            .clickable { onClick?.invoke() }
    )
}

private fun makeSecure(str: String): String {
    return str.replace("http", "https")
}