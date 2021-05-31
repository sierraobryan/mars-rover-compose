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
package com.example.androiddevchallenge.network

import com.example.androiddevchallenge.data.models.Photos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarsRoverService {

    // https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&api_key=DEMO_KEY

    @GET("mars-photos/api/v1/rovers/{rover}/photos")
    suspend fun listPhotosFromCoroutine(
        @Path("rover") rover: String = "curiosity",
        @Query("earth_date") earthDate: String = "2015-6-3",
    ): Photos
}
