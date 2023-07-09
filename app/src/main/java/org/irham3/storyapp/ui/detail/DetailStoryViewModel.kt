package org.irham3.storyapp.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.irham3.storyapp.data.StoryRepository
import javax.inject.Inject

@HiltViewModel
class DetailStoryViewModel @Inject constructor(
    private val storyRepository: StoryRepository
) : ViewModel() {

    suspend fun getDetailStory(id: String)  =
        storyRepository.getDetailStory(id)
}