package org.irham3.storyapp.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.irham3.storyapp.data.AuthRepository
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun register(name: String, email: String, password: String) =
        authRepository.registerUser(name, email, password).asLiveData()

}