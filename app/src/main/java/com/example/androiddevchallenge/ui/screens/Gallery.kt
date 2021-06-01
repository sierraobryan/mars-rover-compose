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

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.androiddevchallenge.ui.MainViewModel
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun GalleryScreen(viewModel: MainViewModel) {
    val photos = viewModel.photosState.collectAsState()

    LazyColumn {
        items(photos.value) { photo ->
            Image(
                painter = rememberCoilPainter(makeSecure(photo.imgSrc), fadeIn = true),
                contentDescription = "Mars Photo on ${photo.earthDate} from ${photo.rover.name}",
            )
        }
    }
}

private fun makeSecure(str: String): String {
    return str.replace("http", "https")
}
