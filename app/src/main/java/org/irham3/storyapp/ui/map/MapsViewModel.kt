package org.irham3.storyapp.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.irham3.storyapp.data.AuthRepository
import org.irham3.storyapp.data.Result
import org.irham3.storyapp.data.StoryRepository
import org.irham3.storyapp.data.remote.response.StoryItem
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val storyRepository: StoryRepository
): ViewModel() {

    fun getAuthToken() : LiveData<String> =
        authRepository.getAuthToken().asLiveData()

    fun getStoriesWithLocation(token: String) : LiveData<Result<List<StoryItem>>> =
        storyRepository.getStoriesWithLocation(token).asLiveData()

}