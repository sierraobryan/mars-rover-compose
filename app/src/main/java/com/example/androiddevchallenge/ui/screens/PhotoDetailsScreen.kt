package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.Image
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.data.models.Photo
import com.example.androiddevchallenge.ui.components.PhotoItem
import com.google.accompanist.coil.rememberCoilPainter

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

@Composable
fun ConstraintPhotoDetails(photo: Photo, isShown: () -> Unit) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    ) {
        val (image, landingLabel, cameraLabel, roverLabel, landingText, cameraText, roverText) = createRefs()
        val itemModifier = Modifier

        PhotoItem(
            photo = photo,
            modifier = itemModifier.constrainAs(image) {
                centerHorizontallyTo(parent)
                top.linkTo(parent.top)
            }
        )
        Text(
            text = "Landing date:",
            modifier = itemModifier.constrainAs(landingLabel) {
                start.linkTo(parent.start)
                top.linkTo(image.bottom)
            }.padding(horizontal = 10.dp)
        )
        Text(
            text = "Camera:",
            modifier = itemModifier.constrainAs(cameraLabel) {
                start.linkTo(parent.start)
                top.linkTo(landingText.bottom)
            }.padding(horizontal = 10.dp)
        )
        Text(
            text = "Rover:",
            modifier = itemModifier.constrainAs(roverLabel) {
                start.linkTo(parent.start)
                top.linkTo(cameraText.bottom)
            }.padding(horizontal = 10.dp)
        )
        Text(
            text = photo.rover.landingDate,
            modifier = itemModifier.constrainAs(landingText) {
                start.linkTo(landingLabel.end)
                top.linkTo(image.bottom)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }.padding(horizontal = 10.dp)
        )
        Text(
            text = photo.camera.fullName,
            modifier = itemModifier.constrainAs(cameraText) {
                start.linkTo(landingLabel.end)
                top.linkTo(landingLabel.bottom)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }.padding(horizontal = 10.dp)
        )
        Text(
            text = photo.rover.name,
            modifier = itemModifier.constrainAs(roverText) {
                start.linkTo(landingLabel.end)
                top.linkTo(roverLabel.top)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }.padding(horizontal = 10.dp)
        )
    }
}

private fun makeSecure(str: String): String {
    return str.replace("http", "https")
}