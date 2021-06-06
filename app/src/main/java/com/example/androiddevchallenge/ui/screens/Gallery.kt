/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.data.models.Camera
import com.example.androiddevchallenge.data.models.Photo
import com.example.androiddevchallenge.data.models.Rover
import com.example.androiddevchallenge.ui.MainViewModel
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun GalleryScreen(viewModel: MainViewModel) {
    val photos = viewModel.photosState.collectAsState()
    var showDetails by remember { mutableStateOf(false) }
    var photoDetail by remember { mutableStateOf(-1) }

    LazyColumn {
        itemsIndexed(photos.value) { index, photo ->
            PhotoListItem(
                photo = photo,
                onClick = {
                    photoDetail = index
                    showDetails = !showDetails
                }
            )
        }
    }

//    Column(modifier = Modifier
//        .verticalScroll(rememberScrollState())
//        .fillMaxSize()) {
//        photos.value.forEachIndexed { index, photo ->
//            PhotoListItem(photo = photo, onClick = {
//                photoDetail = index
//                showDetails = !showDetails })
//
//        }
//    }
    if (showDetails) DetailsView(photo = photos.value[photoDetail])
}
@Composable
fun PhotoListItem(photo: Photo, onClick: () -> Unit) {
    Image(
        painter = rememberCoilPainter(makeSecure(photo.imgSrc), fadeIn = true),
        contentDescription = "Mars Photo on ${photo.earthDate} from ${photo.rover.name}",
        modifier = Modifier.clickable { onClick() }
    )
}
@Composable
fun DetailsView(photo: Photo) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Image(rememberCoilPainter(makeSecure(photo.imgSrc)), contentDescription = "", modifier = Modifier.fillMaxWidth())
        Text(text = "Landing date: " + photo.rover.landingDate)
        Text(text = "Camera: " + photo.camera.fullName)
        Text(text = "Rover:" + photo.rover.name)
    }
}

@Preview
@Composable
fun showPhotoDetails() {
    DetailsView(dummyPhoto)
}

val dummyPhoto = Photo(
    id = 102685,
    sol = 1004,
    camera = Camera(
        id = 20,
        name = "FHAZ",
        roverId = 5,
        fullName = "Front Hazard Avoidance Camera"
    ),
    imgSrc = "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01004/opgs/edr/fcam/FLB_486615455EDR_F0481570FHAZ00323M_.JPG",
    earthDate = "2015-06-03",
    rover = Rover(
        id = 5,
        name = "Curiosity",
        landingDate = "2012-08-06",
        launchDate = "2011-11-26",
        status = "active"
    )
)

private fun makeSecure(str: String): String {
    return str.replace("http", "https")
}
