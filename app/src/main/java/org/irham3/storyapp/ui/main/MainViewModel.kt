package org.irham3.storyapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.irham3.storyapp.data.AuthRepository
import org.irham3.storyapp.data.local.AuthPreferences
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val authRepository: AuthRepository
) : ViewModel() {

    fun getAuthToken() =
        authRepository.getAuthToken().asLiveData()

}