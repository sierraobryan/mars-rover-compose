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
package com.example.androiddevchallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.MainRepository
import com.example.androiddevchallenge.data.models.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _photosState = MutableStateFlow(emptyList<Photo>())
    // The UI collects from this StateFlow to get its state updates
    val photosState: StateFlow<List<Photo>> = _photosState

    // Backing property to avoid state updates from other classes
    private val _photoIndexState = MutableStateFlow(-1)
    // The UI collects from this StateFlow to get its state updates
    val photoIndexState: StateFlow<Int> = _photoIndexState

    fun getPhotos() {
        viewModelScope.launch {
            val result = mainRepository.getPhotos()
            _photosState.value = result.photos
        }
    }

    fun setSelectedIndex(index: Int) {
        _photoIndexState.value = index
    }
}
