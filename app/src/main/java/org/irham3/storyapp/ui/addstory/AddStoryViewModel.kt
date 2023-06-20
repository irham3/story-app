package org.irham3.storyapp.ui.addstory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.irham3.storyapp.data.StoryRepository
import javax.inject.Inject

@HiltViewModel
class AddStoryViewModel @Inject constructor(
    private val storyRepository: StoryRepository
) : ViewModel(){
    fun createNewStory(token: String, imageMultipart: MultipartBody.Part, description: RequestBody) =
        storyRepository.createNewStory(token, description, imageMultipart).asLiveData()
}