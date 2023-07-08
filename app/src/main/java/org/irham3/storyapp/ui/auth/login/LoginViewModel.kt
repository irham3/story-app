package org.irham3.storyapp.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.irham3.storyapp.data.AuthRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun login(email: String, password: String) =
        authRepository.loginUser(email, password).asLiveData()

    fun saveSession() {
        viewModelScope.launch {
            authRepository.saveSession()
        }
    }

    fun getSession() : LiveData<Boolean> =
        authRepository.getSession().asLiveData()

    fun saveAuthToken(authToken: String) {
        viewModelScope.launch {
            authRepository.saveAuthToken(authToken)
        }
    }
}