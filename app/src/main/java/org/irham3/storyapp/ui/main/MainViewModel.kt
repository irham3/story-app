package org.irham3.storyapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.irham3.storyapp.data.AuthRepository
import org.irham3.storyapp.data.StoryRepository
import javax.inject.Inject
import org.irham3.storyapp.data.local.entity.StoryEntity

@HiltViewModel
class MainViewModel @Inject constructor (
    private val authRepository: AuthRepository,
    private val storyRepository: StoryRepository
) : ViewModel() {

    fun getSession() : LiveData<Boolean> =
        authRepository.getSession().asLiveData()

    fun getAuthToken() : LiveData<String> =
        authRepository.getAuthToken().asLiveData()

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    fun getAllStories(token: String) : LiveData<PagingData<StoryEntity>> =
        storyRepository.getAllStories(token).cachedIn(viewModelScope)

}