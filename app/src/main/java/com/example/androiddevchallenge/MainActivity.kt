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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.MainViewModel
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.screens.PhotoList
import com.example.androiddevchallenge.ui.screens.PhotosDetails
import com.example.androiddevchallenge.ui.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.getPhotos()

        setContent {
            val navController = rememberNavController()
            MyTheme(darkTheme = true) {
                MyApp {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ListScreen.route
                    ) {
                        composable(Screen.ListScreen.route) {
                            PhotoList(mainViewModel) { navigateToDetails(navController) }
                        }
                        composable(Screen.DetailsScreen.route) {
                            PhotosDetails(mainViewModel) { navigateToPhotoList(navController) }
                        }
                    }
                }
            }
        }
    }

    private fun navigateToDetails(navController: NavController) {
        navController.navigate(Screen.DetailsScreen.route)
    }

    private fun navigateToPhotoList(navController: NavController) {
        navController.navigateUp()
    }
}

// Start building your app here!
@Composable
fun MyApp(content: @Composable () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        content()
    }
}
