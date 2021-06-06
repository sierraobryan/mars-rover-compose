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
package com.example.androiddevchallenge.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Camera(
    @Json(name = "id") val id: Long?,
    @Json(name = "name") val name: String,
    @Json(name = "rover_id") val roverId: Long?,
    @Json(name = "full_name") val fullName: String
)

@JsonClass(generateAdapter = true)
data class Rover(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "landing_date") val landingDate: String,
    @Json(name = "launch_date") val launchDate: String,
    @Json(name = "status") val status: String,
//    @Json(name = "max_sol") val maxSol: Long?,
//    @Json(name = "max_date") val maxDate: String?,
//    @Json(name = "total_photos") val totalPhotos: Long?,
//    @Json(name = "cameras") val cameras: List<Camera>?
)

@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "id") val id: Long,
    @Json(name = "sol") val sol: Long,
    @Json(name = "camera") val camera: Camera,
    @Json(name = "img_src") val imgSrc: String,
    @Json(name = "earth_date") val earthDate: String,
    @Json(name = "rover") val rover: Rover
)

@JsonClass(generateAdapter = true)
data class Photos(
    @Json(name = "photos") val photos: List<Photo>
)
