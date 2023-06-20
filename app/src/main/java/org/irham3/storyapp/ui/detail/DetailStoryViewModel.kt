package org.irham3.storyapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.irham3.storyapp.data.StoryRepository
import org.irham3.storyapp.data.Result
import org.irham3.storyapp.data.remote.response.DetailStoryResponse
import javax.inject.Inject

@HiltViewModel
class DetailStoryViewModel @Inject constructor(
    private val storyRepository: StoryRepository
) : ViewModel() {

    fun getDetailStory(token: String, id: String) : LiveData<Result<DetailStoryResponse>> =
        storyRepository.getDetailStory(token, id).asLiveData()
}