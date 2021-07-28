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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.androiddevchallenge.data.models.Camera
import com.example.androiddevchallenge.data.models.Photo
import com.example.androiddevchallenge.data.models.Rover
import com.example.androiddevchallenge.ui.MainViewModel

@ExperimentalAnimationApi
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val photos = viewModel
        .photosState
        .collectAsState()
    var showDetails by remember { mutableStateOf(false) }
    var photoDetail by remember { mutableStateOf(-1) }

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

    PhotoList(
        photos = photos.value,
        onClick = { index ->
            photoDetail = index
            showDetails = !showDetails
        }
    )

//    if (showDetails)
//        PhotosDetails(
//            photo = photos.value[photoDetail],
//            isShown = { showDetails = false }
//        )

    AnimatedVisibility(
        visible = showDetails,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        PhotosDetails(
            photo = photos.value[photoDetail],
            isShown = { showDetails = false }
        )
    }
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
        status = "active",
        maxSol = null,
        maxDate = null,
        totalPhotos = null,
        cameras = null
    )
)
